import json
import os
import urllib.request

json_file_path = '/Users/apple/.gemini/antigravity-ide/brain/5d9e67d2-22b3-4bb4-a9d1-62c33052aa45/.system_generated/steps/16/output.txt'
output_dir = '/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin_new/src/screens'

os.makedirs(output_dir, exist_ok=True)

with open(json_file_path, 'r') as f:
    data = json.load(f)

screens = data.get('screens', [])
desktop_screens = [s for s in screens if s.get('deviceType') == 'DESKTOP']

print(f"Found {len(desktop_screens)} desktop screens. Downloading...")

for screen in desktop_screens:
    title = screen.get('title', 'Unknown').replace(' ', '_').replace('&', 'and')
    screen_id = screen.get('name', '').split('/')[-1]
    download_url = screen.get('htmlCode', {}).get('downloadUrl')
    
    if download_url:
        print(f"Downloading {title} ({screen_id})...")
        try:
            req = urllib.request.Request(download_url, headers={'User-Agent': 'Mozilla/5.0'})
            with urllib.request.urlopen(req) as response:
                html_content = response.read().decode('utf-8')
            
            output_file = os.path.join(output_dir, f"{title}.html")
            with open(output_file, 'w') as out_f:
                out_f.write(html_content)
            print(f"Saved {output_file}")
        except Exception as e:
            print(f"Failed to download {title}: {e}")
    else:
        print(f"No download URL for {title}")

print("Done.")

import os
import glob
import re

pages_dir = '/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin_new/src/pages'
files = glob.glob(os.path.join(pages_dir, '*.tsx'))

for filepath in files:
    with open(filepath, 'r') as f:
        content = f.read()

    # Remove aside completely
    content = re.sub(r'<aside.*?</aside>', '', content, flags=re.DOTALL)
    
    # Remove header completely
    content = re.sub(r'<header.*?</header>', '', content, flags=re.DOTALL)

    # Some pages use <main className="...">, we need to strip it to just return the inner content, OR
    # simply leave it but remove the ml-[260px] and pt-[64px] margin/padding since AdminLayout handles it.
    content = re.sub(r'ml-\[260px\]', '', content)
    content = re.sub(r'pt-\[64px\]', '', content)
    content = re.sub(r'min-h-screen', '', content)
    content = re.sub(r'h-screen', '', content)

    # Write back
    with open(filepath, 'w') as f:
        f.write(content)

print("Stripped layout from all pages.")

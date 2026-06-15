import os
import re

html_dir = '/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin_new/src/screens'
output_dir = '/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin_new/src/pages'

os.makedirs(output_dir, exist_ok=True)

def fix_self_closing_tags(html_content):
    # Tags that need to be self-closing in JSX
    tags_to_close = ['img', 'input', 'br', 'hr', 'link', 'meta', 'path', 'circle', 'polygon', 'rect', 'line', 'polyline', 'ellipse']
    for tag in tags_to_close:
        # Match <tag ...> or <tag> and the closing </tag>
        # We replace <tag ...> with <tag ... />
        pattern = re.compile(r'<(%s\b[^>]*?)(?<!/)>' % tag, re.IGNORECASE)
        html_content = pattern.sub(r'<\1 />', html_content)
        # Then remove the closing tag if it exists
        closing_pattern = re.compile(r'</%s\s*>' % tag, re.IGNORECASE)
        html_content = closing_pattern.sub('', html_content)
    return html_content

def convert_html_to_jsx(html_content, component_name):
    # Extract body content
    body_match = re.search(r'<body[^>]*>(.*?)</body>', html_content, re.IGNORECASE | re.DOTALL)
    if not body_match:
        return None
    
    body = body_match.group(1)
    
    # Remove script tags from body
    body = re.sub(r'<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>', '', body, flags=re.IGNORECASE)
    
    # Basic JSX replacements
    body = body.replace('class="', 'className="')
    body = body.replace('for="', 'htmlFor="')
    body = body.replace('<!--', '{/*')
    body = body.replace('-->', '*/}')
    
    # Remove style attributes completely to avoid JSX syntax errors
    body = re.sub(r'\sstyle="[^"]*"', '', body)
    
    # Remove onclick, onsubmit, etc.
    body = re.sub(r'\son[a-z]+="[^"]*"', '', body)
    
    # Escape { and } safely. First we shouldn't have any legit { } except for comments {/* */}
    # Let's temporarily hide comments
    comments = []
    def save_comment(m):
        comments.append(m.group(0))
        return f'__COMMENT_{len(comments)-1}__'
    
    body = re.sub(r'\{\/\*.*?\*\/\}', save_comment, body, flags=re.DOTALL)
    
    # Replace { and } with HTML entities
    body = body.replace('{', '&#123;')
    body = body.replace('}', '&#125;')
    
    # Restore comments
    for i, c in enumerate(comments):
        body = body.replace(f'__COMMENT_{i}__', c)

    body = fix_self_closing_tags(body)
    
    # SVG attributes
    body = body.replace('stroke-width', 'strokeWidth')
    body = body.replace('stroke-linecap', 'strokeLinecap')
    # SVG attributes and React camelCase
    body = body.replace('stroke-width', 'strokeWidth')
    body = body.replace('stroke-linecap', 'strokeLinecap')
    body = body.replace('stroke-linejoin', 'strokeLinejoin')
    body = body.replace('fill-rule', 'fillRule')
    body = body.replace('clip-rule', 'clipRule')
    body = body.replace('stroke-dasharray', 'strokeDasharray')
    body = body.replace('stroke-dashoffset', 'strokeDashoffset')
    body = body.replace('font-variation-settings', 'fontVariationSettings')
    body = body.replace('autofocus', 'autoFocus')
    body = body.replace('maxlength', 'maxLength')
    body = body.replace('spellcheck', 'spellCheck')
    
    # Fix boolean attributes
    body = re.sub(r'\srequired(="")?', ' required', body)
    body = re.sub(r'\schecked(="")?', ' checked', body)
    body = re.sub(r'\sdisabled(="")?', ' disabled', body)
    body = re.sub(r'\sreadonly(="")?', ' readOnly', body)
    body = re.sub(r'\smultiple(="")?', ' multiple', body)
    body = re.sub(r'\sautofocus(="")?', ' autoFocus', body, flags=re.IGNORECASE)
    body = re.sub(r'\sselected(="")?', ' selected', body)
    body = re.sub(r'maxLength="(\d+)"', r'maxLength={\1}', body, flags=re.IGNORECASE)
    
    # Fix rows="4" becoming string in textarea -> wait, rows="4" is fine for textarea in React (can be number or string). But the error says Type 'string' is not assignable to type 'number'. Let's replace rows="4" with rows={4}
    body = re.sub(r'rows="(\d+)"', r'rows={\1}', body)
    
    # Fix self-closing divs if any broke
    jsx = f"""export default function {component_name}() {{
  return (
    <>
      {body}
    </>
  );
}}
"""
    return jsx

for filename in os.listdir(html_dir):
    if filename.endswith('.html'):
        filepath = os.path.join(html_dir, filename)
        component_name = filename[:-5].replace('-', '_')
        
        # Ensure valid component name
        if not component_name[0].isalpha():
            component_name = 'Page' + component_name
            
        with open(filepath, 'r') as f:
            html = f.read()
            
        jsx = convert_html_to_jsx(html, component_name)
        if jsx:
            out_filepath = os.path.join(output_dir, f"{component_name}.tsx")
            with open(out_filepath, 'w') as f:
                f.write(jsx)
            print(f"Converted {filename} to {component_name}.tsx")
        else:
            print(f"Failed to find body in {filename}")

print("Done.")

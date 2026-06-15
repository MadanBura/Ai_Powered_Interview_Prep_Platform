import os
import re

directory = '/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform/ui/screens'

for root, _, files in os.walk(directory):
    for file in files:
        if file.endswith('.kt'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r') as f:
                content = f.read()

            # Find 'bottomBar = {' and remove it and its matching closing brace
            # This is a bit tricky with regex because of nested braces.
            # We can find 'bottomBar = {' and count braces.
            
            idx = content.find('bottomBar = {')
            if idx != -1:
                # Check if it's commented out
                line_start = content.rfind('\n', 0, idx)
                if line_start != -1 and '//' in content[line_start:idx]:
                    continue

                start_idx = idx
                brace_count = 0
                end_idx = -1
                
                # find the first '{'
                first_brace = content.find('{', start_idx)
                if first_brace != -1:
                    brace_count = 1
                    for i in range(first_brace + 1, len(content)):
                        if content[i] == '{':
                            brace_count += 1
                        elif content[i] == '}':
                            brace_count -= 1
                            if brace_count == 0:
                                end_idx = i
                                break
                
                if end_idx != -1:
                    # remove trailing comma or spaces
                    after_end = end_idx + 1
                    while after_end < len(content) and content[after_end] in [' ', '\n', '\r']:
                        after_end += 1
                    if after_end < len(content) and content[after_end] == ',':
                        after_end += 1
                    
                    new_content = content[:start_idx] + content[after_end:]
                    with open(filepath, 'w') as f:
                        f.write(new_content)
                    print(f"Fixed bottomBar in {filepath}")

import os
import re

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform/ui/screens"

for root, _, files in os.walk(base_dir):
    for file in files:
        if file.endswith("Screen.kt"):
            filepath = os.path.join(root, file)
            with open(filepath, "r") as f:
                content = f.read()
            
            # The regex truncation caused:
            # fun ScreenName(
            #     onNavigateForward: () {
            # OR
            # fun ScreenName(onNavigateForward: () {
            
            # We want to replace `onNavigateForward: () {` with `onNavigateForward: () -> Unit) {`
            new_content = re.sub(r'onNavigateForward:\s*\(\)\s*\{', r'onNavigateForward: () -> Unit) {', content)
            
            # Also if there's any `fun ScreenName( {`
            new_content = re.sub(r'fun\s+([a-zA-Z0-9_]+)\(\s*\{', r'fun \1() {', new_content)
            
            if new_content != content:
                with open(filepath, "w") as f:
                    f.write(new_content)

print("Syntax patch applied to Android files.")

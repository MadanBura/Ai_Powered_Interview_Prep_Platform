import os
import glob
import re

src_dir = "/Users/apple/AI-Powered Interview Preparation Platform/backend/src/main/java/com/interview/platform/"

pattern = re.compile(
    r"\s*// Fallback catch-all for any unmatched endpoints during testing\s*"
    r"@RequestMapping\(\"/\*\*\"\)\s*"
    r"public org\.springframework\.http\.ResponseEntity<\?> handleAllFallback\(\)\s*\{\s*"
    r"int status = service\.handle\(\);\s*"
    r"return org\.springframework\.http\.ResponseEntity\.status\(status\)\.build\(\);\s*"
    r"\}",
    re.MULTILINE
)

for root, _, files in os.walk(src_dir):
    for file in files:
        if file.endswith("Controller.java"):
            filepath = os.path.join(root, file)
            with open(filepath, "r") as f:
                content = f.read()
            
            new_content = pattern.sub("", content)
            
            if new_content != content:
                with open(filepath, "w") as f:
                    f.write(new_content)

print("Ambiguous mappings removed.")

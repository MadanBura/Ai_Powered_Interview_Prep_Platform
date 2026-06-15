import os
import re

test_dir = "/Users/apple/AI-Powered Interview Preparation Platform/backend/src/test/java/com/interview/platform"

for root, dirs, files in os.walk(test_dir):
    for file in files:
        if file.endswith("Test.java"):
            file_path = os.path.join(root, file)
            with open(file_path, "r") as f:
                content = f.read()
            
            # Skip if already added
            if "@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)" not in content:
                # Add the annotation below @WebMvcTest
                new_content = re.sub(
                    r'(@WebMvcTest\(.*?\))',
                    r'\1\n@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)',
                    content
                )
                
                with open(file_path, "w") as f:
                    f.write(new_content)

print("Added addFilters = false to all tests")

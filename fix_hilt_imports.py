import os
import glob

screens_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform/ui/screens/"

for root, _, files in os.walk(screens_dir):
    for file in files:
        if file.endswith("Screen.kt"):
            filepath = os.path.join(root, file)
            with open(filepath, "r") as f:
                content = f.read()
            
            if "hiltViewModel()" in content and "androidx.hilt.navigation.compose.hiltViewModel" not in content:
                content = content.replace("import androidx.compose.runtime.Composable", "import androidx.compose.runtime.Composable\nimport androidx.hilt.navigation.compose.hiltViewModel")
                with open(filepath, "w") as f:
                    f.write(content)

print("Hilt ViewModel imports fixed.")

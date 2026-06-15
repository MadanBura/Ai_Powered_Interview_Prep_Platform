import os
import glob

api_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform/data/remote/api/"

for filepath in glob.glob(os.path.join(api_dir, "*.kt")):
    with open(filepath, "r") as f:
        content = f.read()
    
    # Replace ${sessionId} and ${taskId} back to {sessionId} and {taskId}
    content = content.replace("${sessionId}", "{sessionId}")
    content = content.replace("${taskId}", "{taskId}")

    with open(filepath, "w") as f:
        f.write(content)

print("Retrofit paths fixed.")

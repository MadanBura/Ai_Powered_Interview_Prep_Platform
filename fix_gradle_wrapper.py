import os
import shutil

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app"
settings = os.path.join(base_dir, "settings.gradle.kts")
root_build = os.path.join(base_dir, "build.gradle.kts")
app_build = os.path.join(base_dir, "app/build.gradle.kts")
empty_settings = os.path.join(base_dir, "settings.gradle")

# Backup
if os.path.exists(settings):
    shutil.move(settings, settings + ".bak")
if os.path.exists(root_build):
    shutil.move(root_build, root_build + ".bak")
if os.path.exists(app_build):
    shutil.move(app_build, app_build + ".bak")

# Write empty settings.gradle
with open(empty_settings, "w") as f:
    f.write("")

# Run wrapper
os.system(f"cd '{base_dir}' && gradle wrapper --gradle-version 8.4")

# Delete empty settings
if os.path.exists(empty_settings):
    os.remove(empty_settings)

# Restore
if os.path.exists(settings + ".bak"):
    shutil.move(settings + ".bak", settings)
if os.path.exists(root_build + ".bak"):
    shutil.move(root_build + ".bak", root_build)
if os.path.exists(app_build + ".bak"):
    shutil.move(app_build + ".bak", app_build)

print("Wrapper generation attempted with isolation (empty settings.gradle).")

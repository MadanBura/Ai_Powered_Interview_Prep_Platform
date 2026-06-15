import os

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app"
os.makedirs(os.path.join(base_dir, "gradle"), exist_ok=True)

# gradle.properties
gradle_properties = """org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
android.nonTransitiveRClass=true
"""

# libs.versions.toml
libs_versions_toml = """[versions]
agp = "8.2.0"
kotlin = "1.9.22"
ksp = "1.9.22-1.0.17"
core-ktx = "1.12.0"
lifecycle-runtime-ktx = "2.7.0"
activity-compose = "1.8.2"
compose-bom = "2024.02.00"
navigation-compose = "2.7.7"
retrofit = "2.9.0"
okhttp = "4.12.0"
room = "2.6.1"
hilt = "2.50"
coroutines = "1.7.3"
datastore = "1.0.0"
timber = "5.0.1"
compose-compiler = "1.5.8"

[libraries]
core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "core-ktx" }
lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycle-runtime-ktx" }
activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activity-compose" }
compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "compose-bom" }
ui = { group = "androidx.compose.ui", name = "ui" }
ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
material3 = { group = "androidx.compose.material3", name = "material3" }
navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigation-compose" }

retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
converter-moshi = { group = "com.squareup.retrofit2", name = "converter-moshi", version.ref = "retrofit" }
okhttp = { group = "com.squareup.okhttp3", name = "okhttp", version.ref = "okhttp" }
logging-interceptor = { group = "com.squareup.okhttp3", name = "logging-interceptor", version.ref = "okhttp" }

room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }

hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }

kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "coroutines" }
datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastore" }
timber = { group = "com.jakewharton.timber", name = "timber", version.ref = "timber" }

[plugins]
androidApplication = { id = "com.android.application", version.ref = "agp" }
kotlinAndroid = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
"""

with open(os.path.join(base_dir, "gradle.properties"), "w") as f:
    f.write(gradle_properties)

with open(os.path.join(base_dir, "gradle/libs.versions.toml"), "w") as f:
    f.write(libs_versions_toml)

print("Phase 1 & 2 Setup Completed")

import os

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app"

# 1. Update settings.gradle.kts
settings_gradle = """pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "AIInterviewPlatform"
include(":app")
"""

with open(os.path.join(base_dir, "settings.gradle.kts"), "w") as f:
    f.write(settings_gradle)

# Run gradle wrapper before adding complex plugins
os.system(f"cd '{base_dir}' && gradle wrapper --gradle-version 8.4")

# 2. Update Root build.gradle.kts
root_build_gradle = """// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}
"""

with open(os.path.join(base_dir, "build.gradle.kts"), "w") as f:
    f.write(root_build_gradle)

# 3. Update app/build.gradle.kts
app_build_gradle = """plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.interview.platform"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.interview.platform"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\\"http://10.0.2.2:8080/api/v1/\\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    // AndroidX & Compose
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation.compose)

    // Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // DataStore
    implementation(libs.datastore.preferences)

    // Timber
    implementation(libs.timber)
}
"""

with open(os.path.join(base_dir, "app/build.gradle.kts"), "w") as f:
    f.write(app_build_gradle)

print("Phase 3 Setup Completed")

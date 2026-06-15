import os

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main"
pkg_dir = os.path.join(base_dir, "java/com/interview/platform")
res_dir = os.path.join(base_dir, "res")

os.makedirs(pkg_dir, exist_ok=True)
os.makedirs(os.path.join(res_dir, "values"), exist_ok=True)

# 1. AndroidManifest.xml
manifest = """<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />

    <application
        android:name=".MyApp"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.InterviewPlatform"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.InterviewPlatform">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
"""

with open(os.path.join(base_dir, "AndroidManifest.xml"), "w") as f:
    f.write(manifest)

# 2. XML Resources
os.makedirs(os.path.join(res_dir, "xml"), exist_ok=True)
with open(os.path.join(res_dir, "xml", "backup_rules.xml"), "w") as f:
    f.write('<?xml version="1.0" encoding="utf-8"?><full-backup-content><include domain="sharedpref" path="."/></full-backup-content>')
with open(os.path.join(res_dir, "xml", "data_extraction_rules.xml"), "w") as f:
    f.write('<?xml version="1.0" encoding="utf-8"?><data-extraction-rules><cloud-backup><include domain="sharedpref" path="."/></cloud-backup></data-extraction-rules>')

strings_xml = """<resources>
    <string name="app_name">AI Interview Platform</string>
</resources>
"""
with open(os.path.join(res_dir, "values", "strings.xml"), "w") as f:
    f.write(strings_xml)

colors_xml = """<resources>
    <color name="primary">#16A34A</color>
</resources>
"""
with open(os.path.join(res_dir, "values", "colors.xml"), "w") as f:
    f.write(colors_xml)

themes_xml = """<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Theme.InterviewPlatform" parent="android:Theme.Material.Light.NoActionBar" />
</resources>
"""
with open(os.path.join(res_dir, "values", "themes.xml"), "w") as f:
    f.write(themes_xml)

# 3. MyApp.kt
myapp_kt = """package com.interview.platform

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
"""
with open(os.path.join(pkg_dir, "MyApp.kt"), "w") as f:
    f.write(myapp_kt)

# 4. MainActivity.kt
main_activity_kt = """package com.interview.platform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text("AI Interview Platform - Candidate App")
                }
            }
        }
    }
}
"""
with open(os.path.join(pkg_dir, "MainActivity.kt"), "w") as f:
    f.write(main_activity_kt)

print("Phase 4 & 5 Setup Completed")

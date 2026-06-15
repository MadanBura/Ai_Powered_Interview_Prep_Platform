import os
import shutil

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform"
android_app_dir = os.path.join(base_dir, "android-app")
app_dir = os.path.join(base_dir, "app")
new_app_dir = os.path.join(android_app_dir, "app")

# 1. Restructure folders
os.makedirs(android_app_dir, exist_ok=True)
if os.path.exists(app_dir):
    shutil.move(app_dir, new_app_dir)
else:
    os.makedirs(new_app_dir, exist_ok=True)

# 2. Write Gradle files
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

root_build_gradle = """// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}
"""

app_build_gradle = """plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines & Flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
}
"""

with open(os.path.join(android_app_dir, "settings.gradle.kts"), "w") as f:
    f.write(settings_gradle)

with open(os.path.join(android_app_dir, "build.gradle.kts"), "w") as f:
    f.write(root_build_gradle)

with open(os.path.join(new_app_dir, "build.gradle.kts"), "w") as f:
    f.write(app_build_gradle)


# 3. Base Configurations
base_pkg_dir = os.path.join(new_app_dir, "src/main/java/com/interview/platform")
os.makedirs(os.path.join(base_pkg_dir, "data/model"), exist_ok=True)
os.makedirs(os.path.join(base_pkg_dir, "data/remote"), exist_ok=True)
os.makedirs(os.path.join(base_pkg_dir, "di"), exist_ok=True)

result_kt = """package com.interview.platform.data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it)) }
}
"""

auth_interceptor_kt = """package com.interview.platform.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Implementation logic for JWT injection and auto-refresh
        val token = "dummy_jwt_token" // Replace with actual DataStore retrieval
        
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
            
        val response = chain.proceed(request)
        if (response.code == 401) {
            // Handle refresh token or SharedFlow logout event
        }
        return response
    }
}
"""

correlation_id_interceptor_kt = """package com.interview.platform.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.util.UUID
import javax.inject.Inject

class CorrelationIdInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Correlation-Id", UUID.randomUUID().toString())
            .build()
        return chain.proceed(request)
    }
}
"""

network_module_kt = """package com.interview.platform.di

import com.interview.platform.BuildConfig
import com.interview.platform.data.remote.AuthInterceptor
import com.interview.platform.data.remote.CorrelationIdInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        correlationIdInterceptor: CorrelationIdInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(correlationIdInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
"""

with open(os.path.join(base_pkg_dir, "data/model/Result.kt"), "w") as f:
    f.write(result_kt)

with open(os.path.join(base_pkg_dir, "data/remote/AuthInterceptor.kt"), "w") as f:
    f.write(auth_interceptor_kt)

with open(os.path.join(base_pkg_dir, "data/remote/CorrelationIdInterceptor.kt"), "w") as f:
    f.write(correlation_id_interceptor_kt)

with open(os.path.join(base_pkg_dir, "di/NetworkModule.kt"), "w") as f:
    f.write(network_module_kt)

print("Project restructured and base configurations successfully applied.")

package com.interview.platform.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object PreferencesKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_DEPARTMENT = stringPreferencesKey("user_department")
        val USER_TECHNOLOGIES = stringPreferencesKey("user_technologies")
        val USER_EXPERIENCE_LEVEL = stringPreferencesKey("user_experience_level")
        val INTERVIEW_QUESTION_COUNT = androidx.datastore.preferences.core.intPreferencesKey("interview_question_count")
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] ?: false
        }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    val authToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN]
        }

    suspend fun setAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN] = token
        }
    }

    val refreshToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.REFRESH_TOKEN]
        }

    suspend fun setRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.REFRESH_TOKEN] = token
        }
    }

    val userName: Flow<String?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.USER_NAME] }

    suspend fun setUserName(name: String) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.USER_NAME] = name }
    }

    val userEmail: Flow<String?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.USER_EMAIL] }

    suspend fun setUserEmail(email: String) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.USER_EMAIL] = email }
    }

    val userDepartment: Flow<String?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.USER_DEPARTMENT] }

    suspend fun setUserDepartment(department: String) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.USER_DEPARTMENT] = department }
    }

    val userTechnologies: Flow<String?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.USER_TECHNOLOGIES] }

    suspend fun setUserTechnologies(technologies: String) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.USER_TECHNOLOGIES] = technologies }
    }

    val userExperienceLevel: Flow<String?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.USER_EXPERIENCE_LEVEL] }

    suspend fun setUserExperienceLevel(experienceLevel: String) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.USER_EXPERIENCE_LEVEL] = experienceLevel }
    }

    val interviewQuestionCount: Flow<Int?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.INTERVIEW_QUESTION_COUNT] }

    suspend fun setInterviewQuestionCount(count: Int) {
        dataStore.edit { preferences -> preferences[PreferencesKeys.INTERVIEW_QUESTION_COUNT] = count }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

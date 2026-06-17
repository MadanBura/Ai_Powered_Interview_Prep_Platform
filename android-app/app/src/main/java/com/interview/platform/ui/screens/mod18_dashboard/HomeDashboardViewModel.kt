package com.interview.platform.ui.screens.mod18_dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.interview.platform.data.remote.api.Mod02UserProfileApiService
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.interview.platform.data.repository.InterviewSessionManager
import kotlinx.coroutines.flow.firstOrNull

@HiltViewModel
class HomeDashboardViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val profileApi: Mod02UserProfileApiService,
    private val sessionApi: Mod10InterviewSessionApiService,
    private val sessionManager: InterviewSessionManager
) : ViewModel() {

    private val _isNewUser = MutableStateFlow(true)
    val isNewUser: StateFlow<Boolean> = _isNewUser.asStateFlow()

    private val _recentScores = MutableStateFlow<List<com.interview.platform.data.remote.dto.mod10_interview_session.InterviewHistoryDataDto>>(emptyList())
    val recentScores: StateFlow<List<com.interview.platform.data.remote.dto.mod10_interview_session.InterviewHistoryDataDto>> = _recentScores.asStateFlow()

    val userName: StateFlow<String> = userPreferencesRepository.userName
        .map { it ?: "Candidate" }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val userTechnologies: StateFlow<String> = userPreferencesRepository.userTechnologies
        .map { it ?: "" }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val userExperienceLevel: StateFlow<String> = userPreferencesRepository.userExperienceLevel
        .map { it ?: "" }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    init {
        viewModelScope.launch {
            try {
                val profileResponse = profileApi.getProfile()
                if (profileResponse.isSuccessful) {
                    profileResponse.body()?.data?.let { data ->
                        data.name?.let { userPreferencesRepository.setUserName(it) }
                        data.department?.let { userPreferencesRepository.setUserDepartment(it) }
                        data.technologies?.let { userPreferencesRepository.setUserTechnologies(it) }
                        data.experienceLevel?.let {
                            userPreferencesRepository.setUserExperienceLevel(
                                it
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                // Ignore
            }

            try {
                val historyResponse = sessionApi.getInterviewHistory()
                if (historyResponse.isSuccessful) {
                    val data = historyResponse.body()?.data
                    _isNewUser.value = data.isNullOrEmpty()
                    _recentScores.value = data ?: emptyList()
                }
            } catch (e: Exception) {
                // Ignore
            }
        }
    }
    fun startForYouInterview(onSuccess: () -> Unit) {
        viewModelScope.launch {
            sessionManager.selectedDepartmentId =
                userPreferencesRepository.userDepartment.firstOrNull() ?: "Engineering"

            sessionManager.selectedTechnologyName =
                userPreferencesRepository.userTechnologies.firstOrNull() ?: "React"

            sessionManager.selectedExperienceLevelName =
                userPreferencesRepository.userExperienceLevel.firstOrNull() ?: "Mid-Level"

            onSuccess()
        }
    }
}


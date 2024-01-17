package com.hmh.hamyeonham.feature.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.core.network.auth.datastore.HMHNetworkPreference
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingAnswer
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingPageInfo
import com.hmh.hamyeonham.feature.onboarding.model.toSignUpRequest
import com.hmh.hamyeonham.login.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface SignUpEffect {
    data object SignUpSuccess : SignUpEffect
    data object SignUpFail : SignUpEffect
}

data class AppAddState(
    val selectedApp: List<String> = listOf(),
)

data class OnBoardingState(
    val onBoardingAnswer: OnboardingAnswer = OnboardingAnswer(),
    val pageInfo: List<OnboardingPageInfo> = emptyList(),
    val isNextButtonActive: Boolean = false,
    val accessToken: String = "",
)

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val hmhNetworkPreference: HMHNetworkPreference,
) : ViewModel() {
    private val _userResponses = MutableStateFlow(OnboardingAnswer())
    val userResponses = _userResponses.asStateFlow()

    private val _onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingState = _onBoardingState.asStateFlow()

    private val _onboardEffect = MutableSharedFlow<SignUpEffect>()
    val onboardEffect = _onboardEffect.asSharedFlow()

    private val _addState = MutableStateFlow(AppAddState())
    val addState = _addState.asStateFlow()

    init {
        _onBoardingState.value = onBoardingState.value.copy(
            pageInfo = initializeButtonInfoList(),
        )
    }

    fun updateUserResponses(transform: OnboardingAnswer.() -> OnboardingAnswer) {
        val currentState = userResponses.value
        val newState = currentState.transform()
        _userResponses.value = newState
    }

    fun updateState(transform: OnBoardingState.() -> OnBoardingState) {
        val currentState = onBoardingState.value
        val newState = currentState.transform()
        _onBoardingState.value = newState
    }

    fun updateAppAddState(transform: AppAddState.() -> AppAddState) {
        val currentState = addState.value
        val newState = currentState.transform()
        _addState.value = newState

        val challengeApps = newState.selectedApp.map { appCode ->
            OnboardingAnswer.App(appCode = appCode)
        }

        updateState {
            copy(onBoardingAnswer = onBoardingAnswer.copy(apps = challengeApps))
        }
        Log.d("OnBoardingViewModel", "updateAppAddState: ${onBoardingState.value.onBoardingAnswer}")
    }

    private fun initializeButtonInfoList(): List<OnboardingPageInfo> {
        val buttonInfoList = mutableListOf<OnboardingPageInfo>()
        for (index in 0..3) {
            buttonInfoList.add(OnboardingPageInfo(index))
        }
        return buttonInfoList
    }

    fun signUp() {
        viewModelScope.launch {
            val token = onBoardingState.value.accessToken
            val request = onBoardingState.value.onBoardingAnswer
            Log.d("OnBoardingViewModel", "signUp: $request")
            authRepository.signUp(token, request.toSignUpRequest())
                .onSuccess { signUpUser ->
                    signUpUser.let {
                        hmhNetworkPreference.accessToken = it.accessToken
                        hmhNetworkPreference.refreshToken = it.refreshToken
                        hmhNetworkPreference.userId = it.userId
                        hmhNetworkPreference.autoLoginConfigured = true
                    }
                    viewModelScope.launch {
                        _onboardEffect.emit(SignUpEffect.SignUpSuccess)
                    }
                }.onFailure {
                    viewModelScope.launch {
                        _onboardEffect.emit(SignUpEffect.SignUpFail)
                    }
                }
        }
    }
}

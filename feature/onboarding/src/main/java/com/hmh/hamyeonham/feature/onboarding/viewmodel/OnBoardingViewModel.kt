package com.hmh.hamyeonham.feature.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.core.network.auth.datastore.HMHNetworkPreference
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingAnswer
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingPageInfo
import com.hmh.hamyeonham.feature.onboarding.model.toSignUpRequest
import com.hmh.hamyeonham.login.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface OnBoardingEffect {
    data class ActiveNextButton(val isActive: Boolean) : OnBoardingEffect
    data object SignUpSuccess : OnBoardingEffect
    data object SignUpFail : OnBoardingEffect
}

data class OnBoardingState(
    val onBoardingAnswer: OnboardingAnswer = OnboardingAnswer(),
    val pageInfo: List<OnboardingPageInfo> = emptyList(),
    val accessToken: String = "",
)

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val hmhNetworkPreference: HMHNetworkPreference,
) : ViewModel() {
    private val _onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingState = _onBoardingState.asStateFlow()

    // TODO : stateFlow -> sharedFlow
    private val _onboardEffect = MutableSharedFlow<OnBoardingEffect>()
    val onboardEffect = _onboardEffect.asSharedFlow()

    init {
        _onBoardingState.value = onBoardingState.value.copy(
            pageInfo = initializeButtonInfoList(),
        )
    }

    fun changeStateNextButton(isActive: Boolean) {
        viewModelScope.launch {
            _onboardEffect.emit(OnBoardingEffect.ActiveNextButton(isActive))
        }
    }

    fun updateUserResponses(transform: OnboardingAnswer.() -> OnboardingAnswer) {
        val currentState = onBoardingState.value.onBoardingAnswer
        val newState = currentState.transform()
        _onBoardingState.value = onBoardingState.value.copy(onBoardingAnswer = newState)
    }

    fun updateState(transform: OnBoardingState.() -> OnBoardingState) {
        val currentState = onBoardingState.value
        val newState = currentState.transform()
        _onBoardingState.value = newState
    }

    fun setEffect(effect: OnBoardingEffect) {
        viewModelScope.launch {
            _onboardEffect.emit(effect)
        }
    }

    fun signUp() {
        viewModelScope.launch {
            val token = onBoardingState.value.accessToken
            val request = onBoardingState.value.onBoardingAnswer
            runCatching {
                signUpRepository.signUp(token, request.toSignUpRequest())
            }.onSuccess { result ->
                val signUpUser = result.getOrNull()
                signUpUser?.let {
                    hmhNetworkPreference.accessToken = it.accessToken
                    hmhNetworkPreference.refreshToken = it.refreshToken
                    hmhNetworkPreference.userId = it.userId
                    hmhNetworkPreference.autoLoginConfigured = true
                }
                setEffect(OnBoardingEffect.SignUpSuccess)
            }.onFailure {
                setEffect(OnBoardingEffect.SignUpFail)
            }
        }
    }

    private fun initializeButtonInfoList(): List<OnboardingPageInfo> {
        val buttonInfoList = mutableListOf<OnboardingPageInfo>()
        for (index in 0..3) {
            buttonInfoList.add(OnboardingPageInfo(index))
        }
        return buttonInfoList
    }
}

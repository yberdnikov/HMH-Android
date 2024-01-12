package com.hmh.hamyeonham.feature.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingAnswer
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingPageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface OnBoardingEffect {
    data class ActiveNextButton(val isActive: Boolean) : OnBoardingEffect
}

data class OnBoardingState(
    val onBoardingAnswer: OnboardingAnswer = OnboardingAnswer(),
    val pageInfo: List<OnboardingPageInfo> = emptyList(),
)

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _userResponses = MutableStateFlow(OnboardingAnswer())
    val userResponses = _userResponses.asStateFlow()

    private val _onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingState = _onBoardingState.asStateFlow()

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
        val currentState = userResponses.value
        val newState = currentState.transform()
        _userResponses.value = newState
        Log.d("TAG", "${_userResponses.value}")
    }

    private fun initializeButtonInfoList(): List<OnboardingPageInfo> {
        val buttonInfoList = mutableListOf<OnboardingPageInfo>()
        for (index in 0..3) {
            buttonInfoList.add(OnboardingPageInfo(index))
        }
        return buttonInfoList
    }
}

package com.hmh.hamyeonham.feature.onboarding.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingBtnInfo
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingInformation
import com.hmh.hamyeonham.login.model.SignRequestDomain
import com.hmh.hamyeonham.login.repository.SignUpRepository
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

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
) : ViewModel() {
    private lateinit var accessToken: String
    private val _buttonInfoList =
        MutableStateFlow<List<OnboardingBtnInfo>>(initializeButtonInfoList())

    private val _signUpEvent = MutableSharedFlow<SignUpEffect>()
    val signUpEvent = _signUpEvent.asSharedFlow()

    private fun initializeButtonInfoList(): List<OnboardingBtnInfo> {
        val buttonInfoList = mutableListOf<OnboardingBtnInfo>()
        for (index in 0..3) {
            buttonInfoList.add(OnboardingBtnInfo(index, false, ""))
        }
        return buttonInfoList
    }

    val buttonInfoList = _buttonInfoList.asStateFlow()

    private val _canClickActivityNextButton = MutableStateFlow(false)
    val clickNextButtonEnable = _canClickActivityNextButton.asStateFlow()

    fun onClickFragmentBtn(index: Int) {
        _buttonInfoList.value = _buttonInfoList.value.map { buttonInfo ->
            if (buttonInfo.index == index) {
                buttonInfo.copy(isClicked = !buttonInfo.isClicked)
            } else {
                buttonInfo.copy(isClicked = false)
            }
        }
        _canClickActivityNextButton.value = _buttonInfoList.value.any { it.isClicked }
    }

    fun initializeButtonStates() {
        val clickedButton = _buttonInfoList.value.find { it.isClicked }
        _buttonInfoList.value = _buttonInfoList.value.map { buttonInfo ->
            if (clickedButton != null && buttonInfo.index == clickedButton.index) {
                buttonInfo.copy(isClicked = false)
            } else {
                buttonInfo
            }
        }
        _canClickActivityNextButton.value = true
    }

    fun activeActivityNextButton() {
        _canClickActivityNextButton.value = true
    }

    fun setAccessToken(token: String) {
        accessToken = token
    }

    fun signUpWithUserInfo() {
        viewModelScope.launch {
            val selectedButton = _buttonInfoList.value.find { it.isClicked }
            if (selectedButton != null) {
                val onboardingInformation = OnboardingInformation(
                    usuallyUseTime = selectedButton.text,
                    onboarding = OnboardingInformation.Onboarding(
                        averageUseTime = selectedButton.text,
                        problem = listOf(),
                    ),
                    challenge = OnboardingInformation.Challenge(
                        period = -1,
                        goalTime = -1,
                        apps = OnboardingInformation.Apps(
                            appCode = "",
                            goalTime = -1,
                        ),
                    ),
                )
                val request = SignRequestDomain(
                    usuallyUseTime = onboardingInformation.usuallyUseTime,
                    onboarding = onboardingInformation.Onboarding(
                        averageUseTime = onboardingInformation.onboarding.averageUseTime,
                        problem = onboardingInformation.onboarding.problem,
                    ),
                    challenge = SignRequestDomain.Challenge(
                        period = onboardingInformation.challenge.period,
                        goalTime = onboardingInformation.challenge.goalTime,
                        apps = SignRequestDomain.Apps(
                            appCode = onboardingInformation.challenge.apps.appCode,
                            goalTime = onboardingInformation.challenge.apps.goalTime,
                        ),
                    ),
                )
                val result = signUpRepository.signUp(accessToken, request)
                Log.d("UserInformation result", "signUpWithUserInfo: $result")
                result.onSuccess {
                    _signUpEvent.emit(SignUpEffect.SignUpSuccess)
                }.onFailure {
                    _signUpEvent.emit(SignUpEffect.SignUpFail)
                }
            }
        }
        Log.d("UserInformation _buttonInfoList", "signUpWithUserInfo: ${_buttonInfoList.value}")
    }
}

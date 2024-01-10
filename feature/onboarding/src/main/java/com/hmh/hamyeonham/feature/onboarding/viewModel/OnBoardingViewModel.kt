package com.hmh.hamyeonham.feature.onboarding.viewModel

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.feature.onboarding.model.OnBoardingBtnInfo
import com.hmh.hamyeonham.feature.onboarding.model.OnBoardingInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _buttonInfoList =
        MutableStateFlow<List<OnBoardingBtnInfo>>(initializeButtonInfoList())

    private fun initializeButtonInfoList(): List<OnBoardingBtnInfo> {
        val buttonInfoList = mutableListOf<OnBoardingBtnInfo>()
        for (index in 0..3) {
            buttonInfoList.add(OnBoardingBtnInfo(index, false, ""))
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
        _canClickActivityNextButton.value = false
    }

    fun activeActivityNextButton() {
        _canClickActivityNextButton.value = true
    }

    private fun updateOnboardingInformation() {
        val selectedButton = _buttonInfoList.value.find { it.isClicked }
        if (selectedButton != null) {
            val onboardingInformation = OnBoardingInformation(
                usuallyUseTime = selectedButton.text,
                problems = listOf(),
                challenge = listOf(),
                apps = listOf(),
            )
        }
    }
}

package com.hmh.hamyeonham.feature.onboarding.viewModel

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingBtnInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _buttonInfoList = MutableStateFlow<List<OnboardingBtnInfo>>(
        listOf(
            OnboardingBtnInfo(1, false, ""),
            OnboardingBtnInfo(2, false, ""),
            OnboardingBtnInfo(3, false, ""),
            OnboardingBtnInfo(4, false, ""),
        ),
    )
    val buttonInfoList = _buttonInfoList.asStateFlow()

    private val _canClickActivityNextButton = MutableStateFlow(false)
    val canClickActivityNextButton = _canClickActivityNextButton.asStateFlow()

    fun onClickFragmentBtn(index: Int) {
        _buttonInfoList.value = _buttonInfoList.value.map { buttonInfo ->
            if (buttonInfo.index == index) {
                buttonInfo.copy(isClicked = !buttonInfo.isClicked)
            } else {
                buttonInfo
            }
        }

        _canClickActivityNextButton.value = _buttonInfoList.value.any { it.isClicked }
    }

    fun initializeButtonStates() {
        _buttonInfoList.value = _buttonInfoList.value.map { buttonInfo ->
            buttonInfo.copy(isClicked = false)
        }
        _canClickActivityNextButton.value = false
    }

    fun activeActivityNextButton() {
        _canClickActivityNextButton.value = true
    }

    fun updateButtonText(index: Int, text: String) {
        _buttonInfoList.value = _buttonInfoList.value.map { buttonInfo ->
            if (buttonInfo.index == index) {
                buttonInfo.copy(text = text)
            } else {
                buttonInfo
            }
        }
    }
}

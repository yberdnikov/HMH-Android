package com.hmh.hamyeonham.feature.onboarding.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _canClickActivityNextButton = MutableStateFlow(false)
    val canClickActivityNextButton = _canClickActivityNextButton.asStateFlow()

    private val _clickedFragmentBtn1 = MutableStateFlow(false)
    val clickedFragmentBtn1 = _clickedFragmentBtn1.asStateFlow()

    private val _clickedFragmentBtn2 = MutableStateFlow(false)
    val clickedFragmentBtn2 = _clickedFragmentBtn2.asStateFlow()

    private val _clickedFragmentBtn3 = MutableStateFlow(false)
    val clickedFragmentBtn3 = _clickedFragmentBtn3.asStateFlow()

    private val _clickedFragmentBtn4 = MutableStateFlow(false)
    val clickedFragmentBtn4 = _clickedFragmentBtn4.asStateFlow()

    fun onClickFragmentBtn(index: Int) {
        when (index) {
            1 -> _clickedFragmentBtn1.value = !_clickedFragmentBtn1.value
            2 -> _clickedFragmentBtn2.value = !_clickedFragmentBtn2.value
            3 -> _clickedFragmentBtn3.value = !_clickedFragmentBtn3.value
            4 -> _clickedFragmentBtn4.value = !_clickedFragmentBtn4.value
        }

        _canClickActivityNextButton.value =
            _clickedFragmentBtn1.value || _clickedFragmentBtn2.value || _clickedFragmentBtn3.value || _clickedFragmentBtn4.value
    }

    fun initializeButtonStates() {
        _clickedFragmentBtn1.value = false
        _clickedFragmentBtn2.value = false
        _clickedFragmentBtn3.value = false
        _clickedFragmentBtn4.value = false
        _canClickActivityNextButton.value = false
    }
}

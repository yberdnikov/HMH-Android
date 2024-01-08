package com.hmh.hamyeonham.feature.onboarding.viewModel

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.feature.onboarding.model.OnBoardingQuestion
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
            1 -> _clickedFragmentBtn1.value = true
            2 -> _clickedFragmentBtn2.value = true
            3 -> _clickedFragmentBtn3.value = true
            4 -> _clickedFragmentBtn4.value = true
        }
        if (_clickedFragmentBtn1.value || _clickedFragmentBtn2.value || _clickedFragmentBtn3.value || _clickedFragmentBtn4.value) {
            _canClickActivityNextButton.value = true
        }
    }
}

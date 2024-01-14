package com.hmh.hamyeonham.feature.onboarding.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnBoardingPermissionsState(
    val isAccessibilityEnabled: Boolean = false,
    val isUsageStatsEnabled: Boolean = false,
    val isOverlayEnabled: Boolean = false,
) {
    fun checkIsNextButtonActive() =
        isAccessibilityEnabled && isUsageStatsEnabled && isOverlayEnabled
}

@HiltViewModel
class OnBoardingRequestPermissionViewModel @Inject constructor() : ViewModel() {

    private val _permissionsState = MutableStateFlow(OnBoardingPermissionsState())
    val permissionsState = _permissionsState.asStateFlow()

    fun updateState(transform: OnBoardingPermissionsState.() -> OnBoardingPermissionsState) {
        val currentState = permissionsState.value
        val newState = currentState.transform()
        _permissionsState.value = newState
    }
}

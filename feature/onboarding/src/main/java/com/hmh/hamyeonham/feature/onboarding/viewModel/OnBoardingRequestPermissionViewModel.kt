package com.hmh.hamyeonham.feature.onboarding.viewModel

import android.content.Context
import android.provider.Settings
import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.feature.onboarding.OnBoardingAccessibilityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingRequestPermissionViewModel @Inject constructor() : ViewModel() {


    private val _isAccessibilityServiceEnabled = MutableStateFlow(false)
    val isAccessibilityServiceEnabled = _isAccessibilityServiceEnabled.asStateFlow()

}

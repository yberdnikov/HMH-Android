package com.hmh.hamyeonham.feature.onboarding.model

data class OnBoardingPermissionsState(
    val isAccessibilityEnabled: Boolean = false,
    val isUsageStatsEnabled: Boolean = false,
    val isOverlayEnabled: Boolean = false,
)

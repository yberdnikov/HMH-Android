package com.hmh.hamyeonham.feature.onboarding.model

data class OnboardingPageInfo(
    val index: Int,
    val isClicked: Boolean = false,
    val text: List<String> = emptyList(),
)

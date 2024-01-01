package com.hmh.hamyeonham.feature.onboarding

import android.accessibilityservice.AccessibilityService
import com.hmh.hamyeonham.common.context.toast

class OnBoardingAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {
        toast("서비스 onInterrupt ")
    }

    override fun onAccessibilityEvent(event: android.view.accessibility.AccessibilityEvent?) {
        toast("서비스 onAccessibilityEvent ")

    }
}

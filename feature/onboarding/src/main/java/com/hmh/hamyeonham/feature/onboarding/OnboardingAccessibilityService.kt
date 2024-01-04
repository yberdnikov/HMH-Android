package com.hmh.hamyeonham.feature.onboarding

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class OnboardingAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName ?: return
            Log.d("AccessibilityService", "현재 실행 중인 앱 패키지: $packageName")
        }
    }

    override fun onInterrupt() {
    }
}

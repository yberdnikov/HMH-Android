package com.hmh.hamyeonham.feature.onboarding

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class OnBoardingAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName ?: return
            Log.d("OnBoardingAccessibilityService", "onAccessibilityEvent: $packageName")
            if (packageName != "com.google.android.youtube") return
            val intent = Intent(this, LockActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onInterrupt() {}
}

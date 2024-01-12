package com.hmh.hamyeonham.feature.lock

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.usecase.GetUsageGoalsUseCase
import com.hmh.hamyeonham.usagestats.usecase.GetUsageStatFromPackageUseCase
import javax.inject.Inject

class LockAccessibilityService @Inject constructor(
    private val getUsageStatFromPackageUseCase: GetUsageStatFromPackageUseCase,
    private val getUsageGoalsUseCase: GetUsageGoalsUseCase,
) : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            checkUsage(event)
        }
    }

    private fun checkUsage(event: AccessibilityEvent) {
        val packageName = event.packageName?.toString() ?: return
        val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
        val usageStats = getUsageStatFromPackageUseCase(
            startTime = startTime,
            endTime = endTime,
            packageName = packageName
        )
        getUsageGoalsUseCase().firstOrNull { it.packageName == packageName }?.let { myGoal ->
            if (usageStats > myGoal.goalTime) {
                Intent(this, LockActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }.let(::startActivity)
            }
        }
    }

    override fun onInterrupt() {}
}

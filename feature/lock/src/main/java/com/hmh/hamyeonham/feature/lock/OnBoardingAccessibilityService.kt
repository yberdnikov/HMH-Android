package com.hmh.hamyeonham.feature.lock

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.usecase.GetUsageGoalsUseCase
import com.hmh.hamyeonham.usagestats.usecase.GetUsageStatFromPackageUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingAccessibilityService @Inject constructor(
    private val getUsageStatFromPackageUseCase: GetUsageStatFromPackageUseCase,
    private val getUsageGoalsUseCase: GetUsageGoalsUseCase,
) : AccessibilityService() {

    private var usageCheckJob: Job? = null
    private var currentPackageName: String? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        startUsageCheck()
    }

    private fun startUsageCheck() {
        usageCheckJob = GlobalScope.launch {
            while (isActive) {
                delay(10000)
                checkUsage(currentPackageName.toString())
            }
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            currentPackageName = event.packageName.toString()
            checkUsage(currentPackageName.toString())
        }
    }

    private fun checkUsage(packageName: String) {
        val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
        val usageStats = getUsageStatFromPackageUseCase(
            startTime = startTime,
            endTime = endTime,
            packageName = packageName
        )
        getUsageGoalsUseCase().firstOrNull { it.packageName == packageName }?.let { myGoal ->
            if (usageStats > myGoal.goalTime) {
                currentPackageName = null
                Intent(this, LockActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }.let(::startActivity)
            }
        }
    }

    override fun onInterrupt() {}

    override fun onDestroy() {
        usageCheckJob?.cancel()
        super.onDestroy()
    }
}

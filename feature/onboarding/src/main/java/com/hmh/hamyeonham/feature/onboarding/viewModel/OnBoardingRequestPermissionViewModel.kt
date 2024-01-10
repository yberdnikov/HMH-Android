package com.hmh.hamyeonham.feature.onboarding.viewModel

import android.app.usage.UsageStatsManager
import android.content.Context
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.feature.onboarding.OnBoardingAccessibilityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingRequestPermissionViewModel @Inject constructor() : ViewModel() {
    data class PermissionsState(
        val isAccessibilityEnabled: Boolean = false,
        val isUsageStatsEnabled: Boolean = false,
        val isOverlayEnabled: Boolean = false,
    )

    private val _permissionsState = MutableStateFlow(PermissionsState())
    val permissionsState = _permissionsState.asStateFlow()

    fun checkPermissions(context: Context) {
        viewModelScope.launch {
            val accessibilityEnabled = checkAccessibilityServiceEnabled(context)
            val usageStatsEnabled = hasUsageStatsPermission(context)
            val overlayEnabled = hasOverlayPermission(context)

            _permissionsState.value = PermissionsState(
                accessibilityEnabled,
                usageStatsEnabled,
                overlayEnabled,
            )
        }
    }

    private fun checkAccessibilityServiceEnabled(context: Context): Boolean {
        val service =
            context.packageName + "/" + OnBoardingAccessibilityService::class.java.canonicalName
        val enabledServicesSetting = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        )
        return enabledServicesSetting?.contains(service) == true
    }

    private fun hasUsageStatsPermission(context: Context): Boolean {
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            time - 1000 * 60,
            time,
        )
        return stats != null && stats.isNotEmpty()
    }

    private fun hasOverlayPermission(context: Context): Boolean {
        return Settings.canDrawOverlays(context)
    }
}

package com.hmh.hamyeonham.feature.onboarding

import android.accessibilityservice.AccessibilityService
import android.app.AppOpsManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.context.toast
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        if (hasUsageStatsPermission()) {
            toast("권한이 허용되어 있음")
        } else {
            toast("권한이 허용되어 있지 않음")
            requirePermission()
        }
    }

    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun requirePermission() {
        binding.btnPermission.setOnClickListener {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }
    }

    private fun hasUsageStatsPermission(): Boolean {
        val appOps = getSystemService(APP_OPS_SERVICE) as AppOpsManager
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                packageName,
            )
        } else {
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                packageName,
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun isAccessibilityServiceEnabled(service: Class<out AccessibilityService>): Boolean {
        val expectedId = packageName + "/" + service.canonicalName
        val enabledServicesSetting = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        ) ?: return false
        Log.d("TAG", "enabledServicesSetting: $enabledServicesSetting")

        return enabledServicesSetting.split(':').any { it.equals(expectedId, ignoreCase = true) }
    }

    private fun numberPickerTest() {
        binding.run {
//            numberPicker.npCustomHours.minValue = 1
//            numberPicker.npCustomHours.maxValue = 6
//            numberPicker.npCustomMinutes.minValue = 1
//            numberPicker.npCustomMinutes.maxValue = 59
        }
    }
}

package com.hmh.hamyeonham.feature.onboarding

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.context.toast
import com.hmh.hamyeonham.feature.login.LoginActivity
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    private val accessibilitySettingsLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (isAccessibilityServiceEnabled()) {
                toast("접근성 서비스가 활성화되었습니다.")
            } else {
                toast("접근성 서비스가 활성화되지 않았습니다.")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickRequireAccessibilityBtn()
    }

    private fun clickRequireAccessibilityBtn() {
        binding.btnAccessibility.setOnClickListener {
            openAccessibilitySettingsIfNeeded()
        }
        binding.btnUsage.setOnClickListener {
            requestUsageAccessPermission()
        }
        binding.btnDrawOnOthers.setOnClickListener {
            if (!hasOverlayPermission()) {
                requestOverlayPermission()
            } else {
                toast("다른 앱 위에 그리기 권한이 이미 허용되어 있습니다.")
            }
        }
        binding.btnLogin.setOnClickListener {
            if (isAccessibilityServiceEnabled() && hasUsageStatsPermission() && hasOverlayPermission()) {
                toast("모든 권한이 허용되었습니다.")
            } else {
                toast("모든 권한을 허용해주세요.")
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun requestUsageAccessPermission() {
        if (!hasUsageStatsPermission()) {
            try {
                val packageUri = Uri.parse("package:$packageName")
                val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS, packageUri)
                startActivity(intent)
            } catch (e: Exception) {
                val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                startActivity(intent)
            }
        } else {
            toast("사용 정보 접근 권한이 이미 허용되어 있습니다.")
        }
    }

    private fun requestOverlayPermission() {
        val packageUri = Uri.parse("package:$packageName")
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, packageUri)
        startActivity(intent)
    }

    private fun hasOverlayPermission(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val service = packageName + "/" + OnBoardingAccessibilityService::class.java.canonicalName
        val enabledServicesSetting = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        )
        return enabledServicesSetting?.contains(service) == true
    }

    private fun openAccessibilitySettingsIfNeeded() {
        if (!isAccessibilityServiceEnabled()) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            accessibilitySettingsLauncher.launch(intent)
        } else {
            toast("접근성 권한이 이미 허용되어 있습니다.")
        }
    }

    private fun hasUsageStatsPermission(): Boolean {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            time - 1000 * 60,
            time,
        )
        return stats != null && stats.isNotEmpty()
    }
}

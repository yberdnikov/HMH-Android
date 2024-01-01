package com.hmh.hamyeonham.feature.onboarding

import android.accessibilityservice.AccessibilityService
import android.app.AppOpsManager
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
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
            requirePermission()
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
        // 설치된 앱 목록 가져오기
        val installedApps: List<PackageInfo> = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
        Log.d("TAG", "installedApps: $installedApps")

    }

    private fun requirePermission() {
        binding.btnPermission.setOnClickListener {
            moveAppToTopInAccessibilitySettings(AccessibilityService::class.java)
            // startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
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

    // 사용 접근 권한 앱 목록 순서 변경 함수
    private fun moveAppToTopInAccessibilitySettings(service: Class<out AccessibilityService>) {
        val expectedId = packageName + "/" + service.canonicalName
        Log.d("TAG1", "expectedId: $expectedId")
        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))

        val enabledServicesSetting = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        )
        Log.d("TAG2", "enabledServicesSetting: $enabledServicesSetting")

        if (enabledServicesSetting == null) {
            Log.e("TAG3", "Enabled services setting is null")
            return
        }

        // 서비스 목록 순서 재배열
        val serviceList = enabledServicesSetting.split(':').toMutableList()
        serviceList.remove(expectedId)
        serviceList.add(0, expectedId)
        Log.d("TAG4", "serviceList: $serviceList")
        val updatedServicesSetting = serviceList.joinToString(":")
        Log.d("TAG5", "updatedServicesSetting: $updatedServicesSetting")

        // 변경된 서비스 목록을 설정에 저장
        Settings.Secure.putString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
            updatedServicesSetting,
        )
    }
}

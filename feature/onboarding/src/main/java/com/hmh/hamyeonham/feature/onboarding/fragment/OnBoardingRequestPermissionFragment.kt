package com.hmh.hamyeonham.feature.onboarding.fragment

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.fragment.toast
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.OnBoardingAccessibilityService
import com.hmh.hamyeonham.feature.onboarding.R
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingRequestPermissionBinding
import com.hmh.hamyeonham.feature.onboarding.viewModel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingRequestPermissionFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingRequestPermissionBinding::bind)
    private val activityViewModel by activityViewModels<OnBoardingViewModel>()

    // 권한 허용에 대한 결과를 받기 위한 ActivityResultLauncher
    private val accessibilitySettingsLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (isAccessibilityServiceEnabled()) {
                toast(getString(R.string.success_accessibility_settings))
            }
        }

    private val overlayPermissionLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (hasOverlayPermission()) {
                toast(getString(R.string.success_overlay_permission))
            }
        }

    private val usageStatsPermissionLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (hasUsageStatsPermission()) {
                toast(getString(R.string.success_usage_stats_permission))
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentOnBoardingRequestPermissionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickRequireAccessibilityButton()
    }

    // 각 버튼 클릭 시 권한 요청
    private fun clickRequireAccessibilityButton() {
        binding.clOnboardingPermission1.setOnClickListener { // 접근 권한 허용
            if (isAccessibilityServiceEnabled()) {
                toast(getString(R.string.already_accessibility_settings))
            } else {
                requestAccessibilitySettings()
            }
        }
        binding.clOnboardingPermission2.setOnClickListener { // 사용 정보 접근 권한 허용
            if (hasUsageStatsPermission()) {
                toast(getString(R.string.already_usage_stats_permission))
            }
            requestUsageAccessPermission()
        }
        binding.clOnboardingPermission3.setOnClickListener { // 다른 앱 위에 그리기 권한 허용
            if (hasOverlayPermission()) {
                toast(getString(R.string.already_overlay_permission))
            } else {
                requestOverlayPermission()
            }
        }
        if (isAccessibilityServiceEnabled() && hasUsageStatsPermission() && hasOverlayPermission()) {
            activityViewModel.activeActivityNextButton()
        }
    }

    // 접근 권한 허용 되었는지 확인
    private fun isAccessibilityServiceEnabled(): Boolean {
        val service =
            requireContext().packageName + "/" + OnBoardingAccessibilityService::class.java.canonicalName
        val enabledServicesSetting = Settings.Secure.getString(
            requireContext().contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        )
        return enabledServicesSetting?.contains(service) == true
    }

    // 접근 권한 요청 함수
    private fun requestAccessibilitySettings() {
        if (!isAccessibilityServiceEnabled()) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            accessibilitySettingsLauncher.launch(intent)
        }
    }

    // 다른 앱 위에 그리기 허용 되었는지 확인
    private fun hasOverlayPermission(): Boolean {
        return Settings.canDrawOverlays(requireContext())
    }

    // 다른 앱 위에 그리기 권한 요청 함수
    private fun requestOverlayPermission() {
        val packageUri = Uri.parse("package:" + requireContext().packageName)
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, packageUri)
        startActivity(intent)
        overlayPermissionLauncher.launch(intent)
    }

    // 사용 정보 접근 권한 허용 되었는지 확인
    private fun hasUsageStatsPermission(): Boolean {
        val usageStatsManager =
            requireContext().getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            time - 1000 * 60,
            time,
        )
        return stats != null && stats.isNotEmpty()
    }

    // 사용 정보 접근 권한 요청
    private fun requestUsageAccessPermission() {
        try {
            val packageUri = Uri.parse("package:" + requireContext().packageName)
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS, packageUri)
            startActivity(intent)
            usageStatsPermissionLauncher.launch(intent)
        } catch (e: Exception) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
            usageStatsPermissionLauncher.launch(intent)
        }
    }
}

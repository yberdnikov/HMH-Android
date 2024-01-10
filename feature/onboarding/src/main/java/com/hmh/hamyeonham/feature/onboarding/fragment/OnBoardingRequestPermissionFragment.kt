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
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingRequestPermissionBinding
import com.hmh.hamyeonham.feature.onboarding.viewModel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingRequestPermissionFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingRequestPermissionBinding::bind)
    private val activityViewModel by activityViewModels<OnBoardingViewModel>()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentOnBoardingRequestPermissionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.activeActivityNextButton()
        clickRequireAccessibilityButton()
    }

    // 각 버튼 클릭 시 권한 요청
    private fun clickRequireAccessibilityButton() {
        binding.clOnboardingPermission1.setOnClickListener { // 접근 권한 허용
            requestAccessibilitySettings()
        }
        binding.clOnboardingPermission2.setOnClickListener { // 사용 정보 접근 권한 허용
            requestUsageAccessPermission()
        }
        binding.clOnboardingPermission3.setOnClickListener { // 다른 앱 위에 그리기 권한 허용
            if (!hasOverlayPermission()) {
                requestOverlayPermission()
            } else {
                toast("다른 앱 위에 그리기 권한이 이미 허용되어 있습니다.")
            }
        }
        if (isAccessibilityServiceEnabled() && hasUsageStatsPermission() && hasOverlayPermission()) {
            // 다음 버튼 활성화
        } else {
            // 다음 버튼 비활성화
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
        } else {
            // toast("접근성 권한이 이미 허용되어 있습니다.")
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
        if (!hasUsageStatsPermission()) {
            try {
                val packageUri = Uri.parse("package:" + requireContext().packageName)
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
}

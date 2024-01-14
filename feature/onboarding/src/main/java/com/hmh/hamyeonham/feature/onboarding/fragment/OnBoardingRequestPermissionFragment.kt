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
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import com.hmh.hamyeonham.common.fragment.toast
import com.hmh.hamyeonham.common.fragment.viewLifeCycle
import com.hmh.hamyeonham.common.fragment.viewLifeCycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.OnBoardingAccessibilityService
import com.hmh.hamyeonham.feature.onboarding.R
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingRequestPermissionBinding
import com.hmh.hamyeonham.feature.onboarding.viewmodel.OnBoardingPermissionsState
import com.hmh.hamyeonham.feature.onboarding.viewmodel.OnBoardingRequestPermissionViewModel
import com.hmh.hamyeonham.feature.onboarding.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OnBoardingRequestPermissionFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingRequestPermissionBinding::bind)
    private val viewModel by viewModels<OnBoardingRequestPermissionViewModel>()
    private val activityViewModel by activityViewModels<OnBoardingViewModel>()

    private val accessibilitySettingsLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (checkAccessibilityServiceEnabled()) {
                viewModel.updateState { copy(isAccessibilityEnabled = true) }
                toast(getString(R.string.success_accessibility_settings))
            }
        }

    private val overlayPermissionLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (hasOverlayPermission()) {
                viewModel.updateState { copy(isOverlayEnabled = true) }
                toast(getString(R.string.success_overlay_permission))
            }
        }

    private val usageStatsPermissionLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (hasUsageStatsPermission()) {
                viewModel.updateState { copy(isUsageStatsEnabled = true) }
                toast(getString(R.string.success_usage_stats_permission))
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentOnBoardingRequestPermissionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectPermissionState()
        clickRequireAccessibilityButton()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState {
            copy(
                isAccessibilityEnabled = checkAccessibilityServiceEnabled(),
                isUsageStatsEnabled = hasUsageStatsPermission(),
                isOverlayEnabled = hasOverlayPermission(),
            )
        }
    }

    private fun collectPermissionState() {
        viewModel.permissionsState.flowWithLifecycle(viewLifeCycle).onEach { permissionsState ->
            updateNextButtonState(permissionsState)
        }.launchIn(viewLifeCycleScope)
    }

    private fun clickRequireAccessibilityButton() {
        binding.run {
            clOnboardingPermission1.setOnClickListener {
                if (viewModel.permissionsState.value.isAccessibilityEnabled) {
                    toast(getString(R.string.already_accessibility_settings))
                } else {
                    requestAccessibilitySettings()
                }
            }
            clOnboardingPermission2.setOnClickListener {
                if (viewModel.permissionsState.value.isUsageStatsEnabled) {
                    toast(getString(R.string.already_usage_stats_permission))
                } else {
                    requestUsageAccessPermission()
                }
            }
            clOnboardingPermission3.setOnClickListener {
                if (viewModel.permissionsState.value.isOverlayEnabled) {
                    toast(getString(R.string.already_overlay_permission))
                } else {
                    requestOverlayPermission()
                }
            }
        }
    }

    private fun updateNextButtonState(permissionsState: OnBoardingPermissionsState) {
        viewModel.permissionsState.flowWithLifecycle(viewLifeCycle).onEach { permissionsState ->
            permissionsState.run {
                if (isAccessibilityEnabled && isUsageStatsEnabled && isOverlayEnabled) {
                    activityViewModel.updateState { copy(isNextButtonActive = true) }
                }
            }
        }.launchIn(viewLifeCycleScope)
    }

    private fun requestAccessibilitySettings() {
        if (!viewModel.permissionsState.value.isAccessibilityEnabled) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            accessibilitySettingsLauncher.launch(intent)
        }
    }

    private fun requestOverlayPermission() {
        val packageUri = Uri.parse("package:" + requireContext().packageName)
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, packageUri)
        startActivity(intent)
        overlayPermissionLauncher.launch(intent)
    }

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

    private fun checkAccessibilityServiceEnabled(): Boolean {
        return context?.let {
            val service =
                it.packageName + "/" + OnBoardingAccessibilityService::class.java.canonicalName
            val enabledServicesSetting = Settings.Secure.getString(
                it.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
            )
            enabledServicesSetting?.contains(service) == true
        } ?: false
    }

    private fun hasUsageStatsPermission(): Boolean {
        return context?.let {
            val usageStatsManager =
                it.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            val time = System.currentTimeMillis()
            val stats = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                time - 1000 * 60,
                time,
            )
            return stats != null && stats.isNotEmpty()
        } ?: false
    }

    private fun hasOverlayPermission(): Boolean {
        return context?.let { Settings.canDrawOverlays(it) } ?: false
    }
}

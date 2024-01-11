package com.hmh.hamyeonham.feature.onboarding.fragment

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
import com.hmh.hamyeonham.common.fragment.toast
import com.hmh.hamyeonham.common.fragment.viewLifeCycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.R
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingRequestPermissionBinding
import com.hmh.hamyeonham.feature.onboarding.model.OnBoardingPermissionsState
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
            if (viewModel.permissionsState.value.isAccessibilityEnabled) {
                toast(getString(R.string.success_accessibility_settings))
            }
        }

    private val overlayPermissionLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (viewModel.permissionsState.value.isOverlayEnabled) {
                toast(getString(R.string.success_overlay_permission))
            }
        }

    private val usageStatsPermissionLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (viewModel.permissionsState.value.isUsageStatsEnabled) {
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

        viewModel.permissionsState.onEach { permissionsState ->
            permissionsState.run {
                val isPermissionAllGranted =
                    isAccessibilityEnabled && isUsageStatsEnabled && isOverlayEnabled
                activityViewModel.changeStateNextButton(isPermissionAllGranted)
            }
        }.launchIn(viewLifeCycleScope)

        clickRequireAccessibilityButton()
        viewModel.checkPermissions(requireContext())
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
        if (permissionsState.isAccessibilityEnabled && permissionsState.isUsageStatsEnabled && permissionsState.isOverlayEnabled) {
            activityViewModel.changeStateNextButton(true)
        }
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
}

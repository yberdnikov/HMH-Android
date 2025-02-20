package com.hmh.hamyeonham.challenge.appadd

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AppAddActivity : AppCompatActivity() {

    companion object {
        const val SELECTED_APPS = "selected_apps"
        const val GOAL_TIME = "goal_time"
    }

    private val binding by viewBinding(ActivityAppAddBinding::inflate)
    private val viewModel by viewModels<AppAddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        collectState()
    }

    private fun initViews() {
        binding.run {
            vpAppAdd.adapter = AppAddViewPagerAdapter(this@AppAddActivity)
            vpAppAdd.isUserInputEnabled = false

            btAppSelection.setOnClickListener { handleNextClicked() }
            ivBack.setOnClickListener { finish() }
        }

    }

    private fun collectState() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { binding.btAppSelection.isEnabled = it.getAppSelectionList(this).isNotEmpty() }
            .launchIn(lifecycleScope)
    }

    private fun handleNextClicked() {
        binding.vpAppAdd.run {
            when (currentItem) {
                0 -> currentItem = 1
                1 -> finishWithResults()
            }
        }
    }

    private fun finishWithResults() {
        val intent = Intent().apply {
            putExtra(SELECTED_APPS, viewModel.state.value.selectedApps.toTypedArray())
            putExtra(GOAL_TIME, viewModel.state.value.goalTime)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}

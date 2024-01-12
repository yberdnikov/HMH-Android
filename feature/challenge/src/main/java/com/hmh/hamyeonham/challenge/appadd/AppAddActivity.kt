package com.hmh.hamyeonham.challenge.appadd

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppAddBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AppAddActivity : AppCompatActivity() {
    companion object {
        const val SELECTED_APPS = "selected_apps"
    }

    private val binding by viewBinding(ActivityAppAddBinding::inflate)
    private val viewModel by viewModels<AppAddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
        initNextButton()
        collectState()
    }

    private fun collectState() {
        viewModel.appAddState.flowWithLifecycle(lifecycle).onEach {
            binding.btAppSelection.isEnabled = it.selectedApp.isNotEmpty()
        }.launchIn(lifecycleScope)
    }

    private fun initNextButton() {
        binding.run {
            btAppSelection.setOnClickListener {
                handleNextClicked()
            }
        }
    }

    private fun ActivityAppAddBinding.handleNextClicked() {
        when (vpAppAdd.currentItem) {
            0 -> {
                vpAppAdd.currentItem = 1
            }

            1 -> {
                val intent = Intent(this@AppAddActivity, AppAddActivity::class.java).apply {
                    putExtra(
                        SELECTED_APPS,
                        viewModel.appAddState.value.selectedApp.toTypedArray()
                    )
                    putExtra("goal_time", viewModel.appAddState.value.goalTime)
                }
                setResult(RESULT_OK, intent)
                finish()
            }

            else -> Unit
        }
    }


    private fun initViewPager() {
        binding.vpAppAdd.run {
            adapter = AppAddViewPagerAdapter(this@AppAddActivity)
            isUserInputEnabled = false
        }
    }
}

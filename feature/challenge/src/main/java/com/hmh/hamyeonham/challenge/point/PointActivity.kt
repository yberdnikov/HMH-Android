package com.hmh.hamyeonham.challenge.point

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityPointBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PointActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityPointBinding::inflate)
    private val viewModel by viewModels<PointViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        collectPointInfo()
        initAppSelectionRecyclerAdapter()
        collectUserPoint()
    }

    private fun initAppSelectionRecyclerAdapter() {

        Log.d("PointActivity", "initAppSelectionRecyclerAdapter: ${viewModel.pointInfoList.value}")
    }

    private fun collectPointInfo() {
        viewModel.pointInfoList.flowWithLifecycle(lifecycle)
            .onEach { pointInfoList ->
                val adapter = PointAdapter(
                    onButtonClick = {
                        // viewModel.earnChallengePoint(PointInfo(challengeDate = ))
                    },
                )
                binding.rvPoint.adapter = adapter
                adapter.submitList(pointInfoList)
                binding.rvPoint.layoutManager = LinearLayoutManager(this)
                Log.d("PointActivity", "collectPointInfo: ${viewModel.pointInfoList.value}")
            }
            .launchIn(lifecycleScope)
    }

    private fun collectUserPoint() {
        viewModel.currentPointState.flowWithLifecycle(lifecycle)
            .onEach {
                binding.tvPointTotal.text = it.toString()
            }
            .launchIn(lifecycleScope)
    }
}

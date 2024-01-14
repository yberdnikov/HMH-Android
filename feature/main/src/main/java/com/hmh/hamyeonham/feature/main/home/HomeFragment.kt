package com.hmh.hamyeonham.feature.main.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.fragment.viewLifeCycle
import com.hmh.hamyeonham.common.fragment.viewLifeCycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.core.viewmodel.MainViewModel
import com.hmh.hamyeonham.feature.main.R
import com.hmh.hamyeonham.feature.main.databinding.FragmentHomeBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val activityViewModel by activityViewModels<MainViewModel>()

    //    val mediaPlayer = MediaPlayer()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStaticsRecyclerView()
        collectUsageStatsList()
//        binding.vvBlackhole.setVideoURI(videoUri)
//        binding.vvBlackhole.start()
    }

    private fun initStaticsRecyclerView() {
        binding.rvStatics.run {
            adapter = UsageStaticsAdapter()
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun collectUsageStatsList() {
        val usageStaticsAdapter = binding.rvStatics.adapter as? UsageStaticsAdapter
        activityViewModel.mainState.flowWithLifecycle(viewLifeCycle).onEach {
            usageStaticsAdapter?.submitList(it.usageStatsList)
            bindBlackholeVideo(it.usageStatsList[0])
        }.launchIn(viewLifeCycleScope)
    }

    private fun bindBlackholeVideo(totalUsageStatusAndGoal: UsageStatusAndGoal) {
        val uri =
            Uri.parse(setUrifromTotalUsage(totalUsageStatusAndGoal))
        binding.vvBlackhole.run {
            setVideoURI(uri)
            requestFocus()
            start()
        }
    }

    private fun setUrifromTotalUsage(totalUsageStatusAndGoal: UsageStatusAndGoal): String {
        val index = totalUsageStatusAndGoal.usedPercentage / 25 + 1
        val blackholeUri = getString(R.string.base_blackhole_uri) + index.toString()
        return getString(R.string.android_resource) + requireContext().packageName + getString(R.string.raw) + blackholeUri
    }

    override fun onResume() {
        super.onResume()
        binding.vvBlackhole.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.vvBlackhole.pause()
    }
}

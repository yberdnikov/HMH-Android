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
        setBlackholeLoop()
    }

    private fun setBlackholeLoop() {
        binding.vvBlackhole.setOnCompletionListener {
            binding.vvBlackhole.start()
        }
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
            if (it.usageStatsList.isNotEmpty()) {
                bindBlackhole(it.usageStatsList[0])
            }
        }.launchIn(viewLifeCycleScope)
    }

    private fun bindBlackhole(totalUsageStatusAndGoal: UsageStatusAndGoal) {
        val index = totalUsageStatusAndGoal.usedPercentage / 25 + 1
        bindBlackholeVideo(index)
        bindBlackholeDescription(index)
    }

    private fun bindBlackholeVideo(index: Int) {
        val uri =
            Uri.parse(setUrifromIndex(index))
        binding.vvBlackhole.run {
            setVideoURI(uri)
            requestFocus()
            start()
        }
    }

    private fun bindBlackholeDescription(index: Int) {
        val description = when (index) {
            1 -> activityViewModel.getUserName() + getString(R.string.blackhole1)
            2 -> getString(R.string.blackhole2)
            3 -> getString(R.string.blackhole3)
            4 -> getString(R.string.blackhole4)
            5 -> getString(R.string.blackhole5)
            else -> ""
        }
        binding.tvHmhTitle.text = description
    }

    private fun setUrifromIndex(index: Int): String {
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

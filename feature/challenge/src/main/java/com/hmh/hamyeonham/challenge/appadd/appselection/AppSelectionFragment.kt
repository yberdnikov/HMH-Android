package com.hmh.hamyeonham.challenge.appadd.appselection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.challenge.appadd.AppAddViewModel
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.FrargmentAppSelectionBinding

class AppSelectionFragment : Fragment() {
    private val binding by viewBinding(FrargmentAppSelectionBinding::bind)
    private val activityViewModel by activityViewModels<AppAddViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FrargmentAppSelectionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        initAppSelectionRecyclerAdapter()
    }

    private fun initAppSelectionRecyclerAdapter() {
        binding.rvAppSelection.run {
            adapter = AppSelectionAdapter(
                onAppCheckboxClicked = ::onAppCheckboxClicked,
                onAppCheckboxUnClicked = ::onAppCheckboxUnClicked
            )
            layoutManager = LinearLayoutManager(requireContext())
            val usageStaticsAdapter = binding.rvAppSelection.adapter as? AppSelectionAdapter
            usageStaticsAdapter?.submitList(getInstalledApps())
        }
    }

    private fun onAppCheckboxClicked(packageName: String) {
        activityViewModel.updateState {
            copy(selectedApp = selectedApp + packageName)
        }
    }

    private fun onAppCheckboxUnClicked(packageName: String) {
        activityViewModel.updateState {
            copy(selectedApp = selectedApp - packageName)
        }
    }


    private fun getInstalledApps(): List<String> {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfoList = requireContext().packageManager.queryIntentActivities(intent, 0)
        return resolveInfoList.map {
            it.activityInfo.packageName
        }
    }
}

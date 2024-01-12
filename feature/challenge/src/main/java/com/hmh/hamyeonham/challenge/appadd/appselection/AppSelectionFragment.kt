package com.hmh.hamyeonham.challenge.appadd.appselection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.challenge.appadd.AppAddActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.FrargmentAppSelectionBinding

class AppSelectionFragment : Fragment() {
    private val binding by viewBinding(FrargmentAppSelectionBinding::bind)
    private val activityViewModel by activityViewModels<AppSelectionViewModel>()

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
        initAppSelectionButtonListener()
    }

    private fun initAppSelectionRecyclerAdapter() {
        binding.rvAppselection.run {
            adapter = AppSelectionAdapter(
                onAppCheckboxClicked = {
                    onAppCheckboxClicked(it)
                },
                onAppCheckboxUnClicked = {
                    onAppCheckboxUnClicked(it)
                }
            )
            layoutManager = LinearLayoutManager(requireContext())
            val usageStaticsAdapter = binding.rvAppselection.adapter as? AppSelectionAdapter
            usageStaticsAdapter?.submitList(getInstalledApps())
        }
    }

    private fun initAppSelectionButtonListener() {
        binding.btAppselection.setOnClickListener {
            val intent = Intent(requireContext(), AppAddActivity::class.java)
            intent.putExtra(
                AppAddActivity.SELECTED_APPS,
                activityViewModel.selectedApp.toTypedArray()
            )
            requireActivity().run {
                setResult(AppCompatActivity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun onAppCheckboxClicked(packageName: String) {
        activityViewModel.selectedApp.add(packageName)
        enableSelectButton(true)
    }

    private fun onAppCheckboxUnClicked(packageName: String) {
        activityViewModel.selectedApp.remove(packageName)
        if (activityViewModel.nonSelected()) {
            enableSelectButton(false)
        }
    }

    private fun enableSelectButton(appSelectButtonEnable: Boolean) {
        binding.btAppselection.apply {
            isEnabled = appSelectButtonEnable
            isSelected = appSelectButtonEnable
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

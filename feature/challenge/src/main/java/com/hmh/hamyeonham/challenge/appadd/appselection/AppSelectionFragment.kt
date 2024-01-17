package com.hmh.hamyeonham.challenge.appadd.appselection

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.challenge.appadd.AppAddViewModel
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.FrargmentAppSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppSelectionFragment : Fragment() {
    private val binding by viewBinding(FrargmentAppSelectionBinding::bind)
    private val viewModel by viewModels<AppSelectionViewModel>()
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
        initSearchBar()
    }

    private fun initSearchBar() {
        binding.etSearchbar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {}
        }
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
        }
        setViewPager()
    }

    private fun setViewPager() {
        val appSelectionAdapter = binding.rvAppSelection.adapter as? AppSelectionAdapter
        appSelectionAdapter?.submitList(viewModel.getInstalledApps())
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
}

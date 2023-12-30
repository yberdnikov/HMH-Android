package com.hmh.hamyeonham.presentation.usagestat

import CustomItemDecoration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.R
import com.hmh.hamyeonham.databinding.FragmentUsagestatBinding
import com.hmh.hamyeonham.presentation.main.MainViewModel

class UsagestatFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    private var safeBinding: FragmentUsagestatBinding? = null
    private val binding get() = requireNotNull(safeBinding) { "FragmentUsagestatBinding not initialized" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usagestat, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        safeBinding = FragmentUsagestatBinding.bind(view)
        val usagestatAdapter = UsagestatAdapter(viewModel.mockAppUsageList)
        binding.rvUsagestat.adapter = usagestatAdapter
        binding.rvUsagestat.addItemDecoration(CustomItemDecoration())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        safeBinding = null
    }
}

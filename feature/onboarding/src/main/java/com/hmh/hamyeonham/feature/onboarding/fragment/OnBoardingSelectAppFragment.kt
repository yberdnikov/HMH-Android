package com.hmh.hamyeonham.feature.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectAppBinding
import com.hmh.hamyeonham.feature.onboarding.viewModel.OnBoardingViewModel

class OnBoardingSelectAppFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingSelectAppBinding::bind)
    private val activityViewModel by activityViewModels<OnBoardingViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentOnBoardingSelectAppBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.activeActivityNextButton()
    }
}

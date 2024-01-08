package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectAppBinding

class OnBoardingSelectAppFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingSelectAppBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentOnBoardingSelectAppBinding.inflate(inflater, container, false).root
    }
}

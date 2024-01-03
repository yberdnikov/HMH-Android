package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnboardingSelectDataBinding

class OnboardingSelectDataFragment : Fragment() {
    private var _binding: FragmentOnboardingSelectDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOnboardingSelectDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvOnbaordingSelectDataQuestion.text = "하루 평균 휴대폰을 \n얼마나 사용하시나요?"
        binding.btnOnboardingSelectData1.text = "1-4 시간"
        binding.btnOnboardingSelectData2.text = "5-6 시간"
        binding.btnOnboardingSelectData3.text = "7-8 시간"
        binding.btnOnboardingSelectData4.text = "9-10 시간"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

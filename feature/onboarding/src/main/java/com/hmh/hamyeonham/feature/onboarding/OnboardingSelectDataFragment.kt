package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnboardingSelectDataBinding

class OnboardingSelectDataFragment : Fragment() {
    private val binding by viewBinding(FragmentOnboardingSelectDataBinding::bind)

    companion object {
        private const val ARG_QUESTION = "question"
        private const val ARG_BTN1 = "btn1"
        private const val ARG_BTN2 = "btn2"
        private const val ARG_BTN3 = "btn3"
        private const val ARG_BTN4 = "btn4"

        fun newInstance(
            question: String,
            btn1: String,
            btn2: String,
            btn3: String,
            btn4: String,
        ): OnboardingSelectDataFragment {
            val fragment = OnboardingSelectDataFragment()
            val args = Bundle()
            args.putString(ARG_QUESTION, question)
            args.putString(ARG_BTN1, btn1)
            args.putString(ARG_BTN2, btn2)
            args.putString(ARG_BTN3, btn3)
            args.putString(ARG_BTN4, btn4)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentOnboardingSelectDataBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = requireArguments().getString(ARG_QUESTION)
        val btn1 = requireArguments().getString(ARG_BTN1)
        val btn2 = requireArguments().getString(ARG_BTN2)
        val btn3 = requireArguments().getString(ARG_BTN3)
        val btn4 = requireArguments().getString(ARG_BTN4)

        binding.run {
            tvOnboardingSelectDataQuestion.text = question
            btnOnboardingSelectData1.text = btn1
            btnOnboardingSelectData2.text = btn2
            btnOnboardingSelectData3.text = btn3
            btnOnboardingSelectData4.text = btn4
        }
    }
}

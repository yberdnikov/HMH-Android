package com.hmh.hamyeonham.feature.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.OnBoardingFragmentType
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectDataBinding
import com.hmh.hamyeonham.feature.onboarding.viewModel.OnBoardingSelectDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OnBoardingSelectDataFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingSelectDataBinding::bind)
    private val viewModel by viewModels<OnBoardingSelectDataViewModel>()

    companion object {
        private const val ARG_FRAGMENT_TYPE = "ARG_FRAGMENT_TYPE"
        fun newInstance(fragmentType: OnBoardingFragmentType): OnBoardingSelectDataFragment {
            val onBoardingFragment = OnBoardingSelectDataFragment()
            val args = Bundle().apply {
                putString(ARG_FRAGMENT_TYPE, fragmentType.name)
            }
            onBoardingFragment.arguments = args
            return onBoardingFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentOnBoardingSelectDataBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeFragmentType()
        initViews()
    }

    private fun initializeFragmentType() {
        val args = requireArguments().getString(ARG_FRAGMENT_TYPE)?.toOnboardingFragmentType()
        if (args != null) {
            viewModel.initQuestionData(args)
        }
    }

    private fun initViews() {
        viewModel.onBoardingSelectDataState.onEach {
            binding.apply {
                val onBoardingQuestion = it.onBoardingQuestion
                tvOnboardingSelectDataQuestion.text = onBoardingQuestion.title
                tvOnboardingSelectDataDescription.text = onBoardingQuestion.description
                btnOnboardingSelectData1.text = onBoardingQuestion.options.getOrNull(0).orEmpty()
                btnOnboardingSelectData2.text = onBoardingQuestion.options.getOrNull(1).orEmpty()
                btnOnboardingSelectData3.text = onBoardingQuestion.options.getOrNull(2).orEmpty()
                btnOnboardingSelectData4.text = onBoardingQuestion.options.getOrNull(3).orEmpty()
            }
        }.launchIn(lifecycleScope)
    }

    private fun String.toOnboardingFragmentType(): OnBoardingFragmentType {
        return try {
            OnBoardingFragmentType.valueOf(this)
        } catch (e: Exception) {
            OnBoardingFragmentType.SELECT_DATA_TIME
        }
    }
}

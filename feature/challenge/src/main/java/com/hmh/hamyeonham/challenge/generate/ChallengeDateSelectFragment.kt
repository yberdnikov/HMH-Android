package com.hmh.hamyeonham.challenge.generate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.databinding.FragmentSelectDataBinding
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDateSelectFragment : Fragment() {
    private val binding by viewBinding(FragmentSelectDataBinding::bind)
    private val viewModel by activityViewModels<ChallengeGenerateViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSelectDataBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.run {
            tvSelectDataQuestion.text = getString(R.string.select_data_question)
            tvSelectDataDescription.text = getString(R.string.select_data_description)
            listOf(
                btnSelectData1,
                btnSelectData2,
                btnSelectData3,
                btnSelectData4
            ).forEachIndexed { index, button ->
                button.apply {
                    text = getString(R.string.challenge_duration_days, (index + 1) * 7)
                    setOnClickListener { viewModel.updateChallenge { copy(period = (index + 1) * 7) } }
                }
            }
        }
    }
}

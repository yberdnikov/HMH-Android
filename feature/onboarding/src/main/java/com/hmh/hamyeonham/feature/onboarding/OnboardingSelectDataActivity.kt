package com.hmh.hamyeonham.feature.onboarding

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnboardingSelectDataBinding

class OnboardingSelectDataActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnboardingSelectDataBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pagerAdapter = FragmentStateAdapter(this)
        setOnboardingFragmets(pagerAdapter)
        setAdapter(pagerAdapter)
    }

    private fun setOnboardingFragmets(pagerAdapter: FragmentStateAdapter) {
        pagerAdapter.addOnboardingFragments(
            OnboardingSelectDataFragment.newInstance(
                OnboardingQuestionList.OnboardingQuestionTime[0],
                OnboardingQuestionList.OnboardingQuestionTime[1],
                OnboardingQuestionList.OnboardingQuestionTime[2],
                OnboardingQuestionList.OnboardingQuestionTime[3],
                OnboardingQuestionList.OnboardingQuestionTime[4],
                OnboardingQuestionList.OnboardingQuestionTime[5],
            ),
        )
        pagerAdapter.addOnboardingFragments(
            OnboardingSelectDataFragment.newInstance(
                OnboardingQuestionList.OnboardingQuestionProblem[0],
                OnboardingQuestionList.OnboardingQuestionProblem[1],
                OnboardingQuestionList.OnboardingQuestionProblem[2],
                OnboardingQuestionList.OnboardingQuestionProblem[3],
                OnboardingQuestionList.OnboardingQuestionProblem[4],
                OnboardingQuestionList.OnboardingQuestionProblem[5],
            ),
        )
        pagerAdapter.addOnboardingFragments(
            OnboardingSelectDataFragment.newInstance(
                OnboardingQuestionList.OnboardingChallengePeriod[0],
                OnboardingQuestionList.OnboardingChallengePeriod[1],
                OnboardingQuestionList.OnboardingChallengePeriod[2],
                OnboardingQuestionList.OnboardingChallengePeriod[3],
                OnboardingQuestionList.OnboardingChallengePeriod[4],
                OnboardingQuestionList.OnboardingChallengePeriod[5],
            ),
        )
        pagerAdapter.addOnboardingFragments(SelectScreentimeGoalFragment())
        pagerAdapter.addOnboardingFragments(RequestPermissionFragment())
        pagerAdapter.addOnboardingFragments(SelectAppFragment())
        pagerAdapter.addOnboardingFragments(SelectUseTimeGoalFragment())
    }

    private fun setAdapter(pagerAdapter: FragmentStateAdapter) {
        binding.vpOnboardingContainer.adapter = pagerAdapter
        binding.vpOnboardingContainer.isUserInputEnabled = false
        binding.btnOnboardingNext.setOnClickListener {
            val currentItem = binding.vpOnboardingContainer.currentItem
            if (currentItem < pagerAdapter.itemCount - 1) {
                binding.vpOnboardingContainer.currentItem = currentItem + 1
            }
            if (currentItem == pagerAdapter.itemCount - 1) {
                pagerAdapter.removeOnboardingFragments()
                startActivity(
                    Intent(this, DoneSingUpActivity::class.java).addFlags(
                        FLAG_ACTIVITY_NO_ANIMATION,
                    ),
                )
            }
        }
    }
}

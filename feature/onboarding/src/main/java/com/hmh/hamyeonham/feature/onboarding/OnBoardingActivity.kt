package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (fragment1, fragment2) = setOnboardingFragment()

        addFragment(fragment1, "fragment1_tag")
        binding.btnOnboardingNext.setOnClickListener {
            replaceFragment(fragment2, "fragment2_tag")
        }
    }

    private fun setOnboardingFragment(): Pair<OnboardingSelectDataFragment, OnboardingSelectDataFragment> {
        val fragment1 = OnboardingSelectDataFragment.newInstance(
            "하루 평균 휴대폰을 \n" +
                "얼마나 사용하시나요?",
            "1-4시간",
            "4-8시간",
            "8-12시간",
            "12시간 이상",
        )

        val fragment2 = OnboardingSelectDataFragment.newInstance(
            "휴대폰을 사용할 때\n" +
                "어떤 문제를 겪고 계시나요?",
            "중독이 너무 심해요",
            "무의식적으로 사용하게 돼요",
            "스스로 제어가 안돼요",
            "일상생활에 영향을 끼쳐요",
        )
        return Pair(fragment1, fragment2)
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.flOnboardingContainer.id, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.flOnboardingContainer.id, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}

package com.hmh.hamyeonham.challenge.generate

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityChallengeGenerateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeGenerateActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityChallengeGenerateBinding::inflate)
    private val viewModel by viewModels<ChallengeGenerateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        binding.vpChallengeGenerate.run {
            adapter = ChallengeGenerateViewPagerAdapter(this@ChallengeGenerateActivity)
            isUserInputEnabled = false
        }
    }
}

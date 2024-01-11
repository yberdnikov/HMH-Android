package com.hmh.hamyeonham.feature.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.navigation.NavigationProvider
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var loginViewPagerAdapter: LoginViewPagerAdapter

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivKakaoLogin.setOnClickListener {
            viewModel.loginWithKakaoApp(this)
        }
        setLoginViewPager()
        handleKakaoLoginSuccess()
    }

    private fun handleKakaoLoginSuccess() {
        viewModel.kakaoLoginState.flowWithLifecycle(lifecycle).onEach { state ->
            if (state.isSuccessResult) {
                // 닉네임 출력
                Log.d("LoginActivity", "닉네임: ${state.kakaoNickname}")
                moveToOnBoardingActivity()
            }
        }.launchIn(lifecycleScope)
    }

    private fun setLoginViewPager() {
        val loginViewImageList = listOf(
            R.drawable.login_sample_rectagle_viewpager,
            R.drawable.login_sample_rectagle_viewpager,
            R.drawable.login_sample_rectagle_viewpager,
        )

        loginViewPagerAdapter = LoginViewPagerAdapter(loginViewImageList)
        binding.run {
            vpLogin.adapter = loginViewPagerAdapter
            indicatorLoginDots.attachTo(binding.vpLogin)
        }
    }

    private fun moveToOnBoardingActivity() {
        startActivity(navigationProvider.toOnBoarding())
        finish()
    }
}

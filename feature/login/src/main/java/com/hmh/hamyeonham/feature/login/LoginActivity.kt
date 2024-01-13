package com.hmh.hamyeonham.feature.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.context.toast
import com.hmh.hamyeonham.common.navigation.NavigationProvider
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.login.databinding.ActivityLoginBinding
import com.hmh.hamyeonham.feature.onboarding.OnBoardingActivity
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
        viewModel.kakaoLoginEvent.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is LoginEffect.LoginSuccess -> moveToMainActivity()

                is LoginEffect.LoginFail -> toast(getString(R.string.fail_kakao_login))
                is LoginEffect.RequireSignUp -> moveToOnBoardingActivity(state.token)
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

    private fun moveToOnBoardingActivity(accessToken: String? = null) {
        val intent = navigationProvider.toOnBoarding()
        accessToken?.let {
            intent.putExtra(OnBoardingActivity.EXTRA_ACCESS_TOKEN, it)
        }
        startActivity(intent)
        finish()
    }

    private fun moveToMainActivity() {
        startActivity(navigationProvider.toMain())
        finish()
    }
}

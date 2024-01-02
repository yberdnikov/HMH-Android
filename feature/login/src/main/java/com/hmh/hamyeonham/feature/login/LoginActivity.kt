package com.hmh.hamyeonham.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.context.toast
import com.hmh.hamyeonham.feature.login.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        when {
            error != null -> {
            }

            token != null -> {
                moveToUserInfoActivity()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            loginWithKakaoApp()
        }
    }

    private fun loginWithKakaoApp() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    toast("카카오 로그인 실패")
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        toast("다시 로그인 해주세요.")
                        return@loginWithKakaoTalk
                    }
                    loginWithKakaoAccount()
                } else if (token != null) {
                    toast("카카오 로그인 성공")
                    moveToUserInfoActivity()
                }
            }
        } else {
            loginWithKakaoAccount()
        }
    }

    private fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }

    private fun moveToUserInfoActivity() {
        startActivity(Intent(this, UserInfoActivity::class.java))
        finish()
    }
}

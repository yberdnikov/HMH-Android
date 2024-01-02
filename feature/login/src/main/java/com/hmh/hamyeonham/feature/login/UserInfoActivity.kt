package com.hmh.hamyeonham.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.context.toast
import com.hmh.hamyeonham.feature.login.databinding.ActivityLoginBinding
import com.hmh.hamyeonham.feature.login.databinding.ActivityUserInfoBinding
import com.kakao.sdk.user.UserApiClient

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getKakaoUserNickname()
        binding.btnLoginLogout.setOnClickListener {
            logoutFromKakao()
        }
    }

    private fun getKakaoUserNickname() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                moveToLogin()
            } else if (user != null) {
                val kakaoNickname = user.kakaoAccount?.profile?.nickname
                binding.tvLoginNickname.text = kakaoNickname
            }
        }
    }

    private fun logoutFromKakao() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                toast("다시 로그아웃 해주세요.")
            } else {
                toast("로그아웃 되었습니다.")
                moveToLogin()
            }
        }
    }

    private fun moveToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}

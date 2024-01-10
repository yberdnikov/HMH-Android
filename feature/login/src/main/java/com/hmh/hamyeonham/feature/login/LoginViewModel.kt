package com.hmh.hamyeonham.feature.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _isSuccessKakaoLogin = MutableStateFlow(true)
    val isSuccessKakaoLogin: StateFlow<Boolean> = _isSuccessKakaoLogin

    private var kakaoAccountCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error == null && token != null) {
            _isSuccessKakaoLogin.value = true
        }
    }

    private var kakaoAppCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            if (!(error is ClientError && error.reason == ClientErrorCause.Cancelled)) {
                _isSuccessKakaoLogin.value = false
            }
        } else if (token != null) {
            _isSuccessKakaoLogin.value = true
        }
    }

    fun loginWithKakaoApp(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(
                context = context,
                callback = kakaoAppCallback,
            )
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                context = context,
                callback = kakaoAccountCallback,
            )
        }
    }
}

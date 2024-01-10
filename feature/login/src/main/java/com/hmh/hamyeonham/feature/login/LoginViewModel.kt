package com.hmh.hamyeonham.feature.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _isSuccessKakaoLogin = MutableStateFlow(false)
    val isSuccessKakaoLogin: StateFlow<Boolean> = _isSuccessKakaoLogin

    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> = _accessToken

    private val _refreshToken = MutableStateFlow<String?>(null)
    val refreshToken: StateFlow<String?> = _refreshToken


    private var kakaoAccountCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error == null && token != null) {
            _accessToken.value = token.accessToken
            _refreshToken.value = token.refreshToken
            _isSuccessKakaoLogin.value = true
            Log.d("LoginViewModel", "accessToken: ${_accessToken.value}, refreshToken: ${_refreshToken.value}")
        }
    }

    private var kakaoAppCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            if (!(error is ClientError && error.reason == ClientErrorCause.Cancelled)) {
                _isSuccessKakaoLogin.value = false
            }
        } else if (token != null) {
            _accessToken.value = token.accessToken
            _refreshToken.value = token.refreshToken
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

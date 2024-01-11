package com.hmh.hamyeonham.feature.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    data class KakaoLoginState(
        val isSuccessResult: Boolean = false,
        val accessToken: String? = null,
        val refreshToken: String? = null,
        val kakaoNickname: String? = null,
    )

    private val _kakaoLoginState = MutableStateFlow(KakaoLoginState())
    val kakaoLoginState = _kakaoLoginState.asStateFlow()

    private fun updateState(transform: KakaoLoginState.() -> KakaoLoginState) {
        val currentState = kakaoLoginState.value
        val newState = currentState.transform()
        _kakaoLoginState.value = newState
    }

    private var kakaoAccountCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error == null && token != null) {
            updateState {
                copy(
                    isSuccessResult = true,
                    accessToken = token.accessToken,
                    refreshToken = token.refreshToken,
                )
            }
        } else if (token != null) {
            updateState {
                copy(
                    isSuccessResult = false,
                    accessToken = "",
                    refreshToken = "",
                )
            }
        }
    }

    private var kakaoAppCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            if (!(error is ClientError && error.reason == ClientErrorCause.Cancelled)) {
                updateState {
                    copy(
                        isSuccessResult = false,
                        accessToken = "",
                        refreshToken = "",
                    )
                }
            }
        } else if (token != null) {
            updateState {
                copy(
                    isSuccessResult = true,
                    accessToken = token.accessToken,
                    refreshToken = token.refreshToken,
                )
            }
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

    fun getKakaoUserNickname() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                // 닉네임 정보 얻기 실패 시
            } else if (user != null) {
                val kakaoNickname = user.kakaoAccount?.profile?.nickname
                updateState {
                    copy(
                        kakaoNickname = kakaoNickname,
                    )
                }
            }
        }
    }
}

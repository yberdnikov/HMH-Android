package com.hmh.hamyeonham.feature.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.login.repository.LoginRepository
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

sealed interface LoginEffect {
    data object LoginSuccess : LoginEffect
    data object LoginFail : LoginEffect
    data class RequireSignUp(val token: String) : LoginEffect
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _kakaoLoginEvent = MutableSharedFlow<LoginEffect>()
    val kakaoLoginEvent = _kakaoLoginEvent.asSharedFlow()

    fun loginWithKakaoApp(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    loginWithKakaoAccount(context)
                } else if (token != null) {
                    viewModelScope.launch {
                        loginRepository.login(token.accessToken).onSuccess {
                            _kakaoLoginEvent.emit(LoginEffect.LoginSuccess)
                        }.onFailure {
                            if (it is HttpException && it.code() == 403) {
                                _kakaoLoginEvent.emit(LoginEffect.RequireSignUp(token.accessToken))
                            } else {
                                _kakaoLoginEvent.emit(LoginEffect.LoginFail)
                            }
                        }
                    }
                }
            }
        } else {
            loginWithKakaoAccount(context)
        }
    }

    private fun loginWithKakaoAccount(context: Context) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                // 닉네임 정보 얻기 실패 시
            } else if (token != null) {
                viewModelScope.launch {
                    loginRepository.login(token.accessToken).onSuccess {
                        _kakaoLoginEvent.emit(LoginEffect.LoginSuccess)
                    }.onFailure {
                        if (it is HttpException && it.code() == 403) {
                            _kakaoLoginEvent.emit(LoginEffect.RequireSignUp(token.accessToken))
                        } else {
                            _kakaoLoginEvent.emit(LoginEffect.LoginFail)
                        }
                    }
                }
            }
        }
    }

    fun getKakaoUserNickname() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                // 닉네임 정보 얻기 실패 시
            } else if (user != null) {
                val kakaoNickname = user.kakaoAccount?.profile?.nickname
            }
        }
    }
}

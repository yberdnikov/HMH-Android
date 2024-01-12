package com.hmh.hamyeonham.login

import com.hmh.hamyeonham.core.network.login.LoginService
import com.hmh.hamyeonham.core.network.login.model.LoginRequest
import com.hmh.hamyeonham.login.mapper.toLogin
import com.hmh.hamyeonham.login.model.Login
import com.hmh.hamyeonham.login.repository.LoginRepository
import javax.inject.Inject

class DefaultLoginRepository @Inject constructor(
    private val loginService: LoginService
) : LoginRepository {

    override suspend fun login(accessToken: String): Result<Login> {
        val request = LoginRequest("KAKAO")
        val bearerToken = "Bearer $accessToken"
        return runCatching {
            loginService.login(bearerToken, request).data.toLogin()
        }
    }
}

package com.hmh.hamyeonham.login

import com.hmh.hamyeonham.core.network.SignUp.SignUpService
import com.hmh.hamyeonham.core.network.SignUp.model.SignUpResponse
import com.hmh.hamyeonham.core.network.login.model.LoginRequest
import com.hmh.hamyeonham.login.mapper.toSignUp
import com.hmh.hamyeonham.login.model.SignRequestDomain
import com.hmh.hamyeonham.login.model.SignUpResponseDomain
import com.hmh.hamyeonham.login.repository.SignUpRepository
import javax.inject.Inject

class DefaultSignUpRepository @Inject constructor(
    private val signUpService: SignUpService,
) : SignUpRepository {

    override suspend fun signUp(
        accessToken: String,
        signUpRequest: SignRequestDomain,
    ): Result<SignUpResponseDomain> {
        return runCatching {
            val socialPlatform = "KAKAO"
            val bearerToken = "Bearer $accessToken"
            val signUpResponse: SignUpResponse = signUpService.signUp(bearerToken, socialPlatform, signUpRequest).data
            SignUpResponseDomain(
                userId = signUpResponse.userId,
                accessToken = signUpResponse.accessToken,
                refreshToken = signUpResponse.refreshToken
            )
        }
    }
}

package com.hmh.hamyeonham.login

import com.hmh.hamyeonham.core.network.service.SignUpService
import com.hmh.hamyeonham.login.model.SignRequestDomain
import com.hmh.hamyeonham.login.model.SignUpUser
import com.hmh.hamyeonham.login.repository.SignUpRepository
import javax.inject.Inject

class DefaultSignUpRepository @Inject constructor(
    private val signUpService: SignUpService,
) : SignUpRepository {

    override suspend fun signUp(
        accessToken: String,
        signUpRequest: SignRequestDomain,
    ): Result<SignUpUser> {
        return runCatching {
            val bearerToken = "Bearer $accessToken"
            signUpService.signUp(bearerToken, "Android", signUpRequest).data.toSignUpUser()
        }
    }
}

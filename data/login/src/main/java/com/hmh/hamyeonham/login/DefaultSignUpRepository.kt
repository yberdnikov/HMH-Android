package com.hmh.hamyeonham.login

import com.hmh.hamyeonham.core.network.SignUp.SignUpService
import com.hmh.hamyeonham.core.network.SignUp.model.SignUpRequest
import com.hmh.hamyeonham.login.mapper.toSignUp
import com.hmh.hamyeonham.login.model.Login
import com.hmh.hamyeonham.login.repository.SignUpRepository
import javax.inject.Inject

class DefaultSignUpRepository @Inject constructor(
    private val signUpService: SignUpService,
) : SignUpRepository {

    override suspend fun signUp(accessToken: String, signUpRequest: SignUpRequest): Result<Login> {
        return runCatching {
            signUpService.signUp(signUpRequest).data.toSignUp()
        }
    }
}

package com.hmh.hamyeonham.login.repository

import com.hmh.hamyeonham.login.model.SignRequestDomain
import com.hmh.hamyeonham.login.model.SignUpUser

interface SignUpRepository {
    suspend fun signUp(accessToken: String, signUpRequest: SignRequestDomain): Result<SignUpUser>
}

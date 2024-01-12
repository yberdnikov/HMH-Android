package com.hmh.hamyeonham.login.repository

import com.hmh.hamyeonham.login.model.Login
import com.hmh.hamyeonham.login.model.SignUp

interface SignUpRepository {
    suspend fun signUp(accessToken: String, signUpRequest: SignUp): Result<Login>
}

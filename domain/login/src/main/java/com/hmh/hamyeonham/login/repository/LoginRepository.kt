package com.hmh.hamyeonham.login.repository

import com.hmh.hamyeonham.login.model.Login

interface LoginRepository {
    suspend fun login(accessToken: String): Result<Login>
}

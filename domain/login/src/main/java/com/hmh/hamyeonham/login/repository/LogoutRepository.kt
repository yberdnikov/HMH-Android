package com.hmh.hamyeonham.login.repository

import com.hmh.hamyeonham.login.model.Login
interface LogoutRepository {
    suspend fun logout(): Result<Unit>
}
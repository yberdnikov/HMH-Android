package com.hmh.hamyeonham.login.repository

interface WithdrawalRepository {
    suspend fun withdrawal(accessToken: String): Result<Unit>
}

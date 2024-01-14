package com.hmh.hamyeonham.login

import com.hmh.hamyeonham.core.network.login.LoginService
import com.hmh.hamyeonham.login.repository.WithdrawalRepository
import javax.inject.Inject

class DefaultWithdrawalRepository @Inject constructor(
    private val withdrawalService: LoginService,
) : WithdrawalRepository {

    override suspend fun withdrawal(accessToken: String): Result<Unit> {
        val bearerToken = "Bearer $accessToken"
        return runCatching {
            withdrawalService.withdrawal(bearerToken)
        }
    }
}

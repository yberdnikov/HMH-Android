package com.hmh.hamyeonham.login.mapper

import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.core.network.challenge.model.ChallengeResponse

internal fun ChallengeResponse.toChallengeStatus(): ChallengeStatus {
    return ChallengeStatus(
        apps.map {
            ChallengeStatus.AppGoal(it.appCode, (it.appGoalTime / 1000 / 60 / 60).toInt())
        },
        statuses.filter { it != "NONE" }
            .map {
                when (it) {
                    "UNEARNED" -> true
                    "EARNED" -> true
                    "FAILURE" -> false
                    else -> false
                }
            },
        goalTime,
        period
    )
}

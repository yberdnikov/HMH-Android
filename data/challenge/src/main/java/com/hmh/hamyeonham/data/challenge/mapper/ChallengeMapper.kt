package com.hmh.hamyeonham.data.challenge.mapper

import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.core.network.challenge.model.ChallengeResponse

internal fun ChallengeResponse.toChallengeStatus(): ChallengeStatus {
    return ChallengeStatus(
        apps.map {
            ChallengeStatus.AppGoal(it.appCode, (it.appGoalTime / 1000 / 60 / 60).toInt())
        },
        statuses.toStatusList(),
        goalTime,
        period,
    )
}

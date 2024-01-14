package com.hmh.hamyeonham.data.challenge.mapper

import com.hmh.hamyeonham.challenge.model.Challenge
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.core.network.challenge.model.ChallengeRequest
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

internal fun Challenge.toChallengeRequest(): ChallengeRequest {
    return ChallengeRequest(
        goalTime = goalTime,
        period = period
    )
}


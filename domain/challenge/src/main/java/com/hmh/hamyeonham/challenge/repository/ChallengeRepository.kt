package com.hmh.hamyeonham.challenge.repository

import com.hmh.hamyeonham.challenge.model.Challenge
import com.hmh.hamyeonham.challenge.model.ChallengeStatus

interface ChallengeRepository {
    suspend fun getChallengeData(): Result<ChallengeStatus>
    suspend fun postChallengeData(request: Challenge): Result<ChallengeStatus>
}

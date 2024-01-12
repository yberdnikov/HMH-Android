package com.hmh.hamyeonham.challenge.repository

import com.hmh.hamyeonham.challenge.model.ChallengeStatus

interface ChallengeRepository {
    suspend fun getChallengeData(): ChallengeStatus
}

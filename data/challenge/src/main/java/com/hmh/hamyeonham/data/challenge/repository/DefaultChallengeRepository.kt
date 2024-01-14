package com.hmh.hamyeonham.data.challenge.repository

import com.hmh.hamyeonham.challenge.model.Challenge
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.challenge.repository.ChallengeRepository
import com.hmh.hamyeonham.core.network.challenge.ChallengeService
import com.hmh.hamyeonham.data.challenge.mapper.toChallengeRequest
import com.hmh.hamyeonham.data.challenge.mapper.toChallengeStatus
import javax.inject.Inject

class DefaultChallengeRepository @Inject constructor(private val challengeService: ChallengeService) :
    ChallengeRepository {
    override suspend fun getChallengeData(): Result<ChallengeStatus> {
        return runCatching { challengeService.getChallenge().data.toChallengeStatus() }
    }

    override suspend fun postChallengeData(request: Challenge): Result<ChallengeStatus> {
        return runCatching {
            challengeService.postChallenge(request.toChallengeRequest()).data.toChallengeStatus()
        }
    }
}

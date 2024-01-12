package com.hmh.hamyeonham.data.challenge

import retrofit2.http.PATCH

interface ChallengeService {
    @PATCH
    suspend fun updateChallenge()
}

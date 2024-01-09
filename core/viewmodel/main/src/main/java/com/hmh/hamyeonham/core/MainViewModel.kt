package com.hmh.hamyeonham.core

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class MainState(
    val challengeStatus: ChallengeStatus
)

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _mainState = MutableStateFlow(MainState(ChallengeStatus()))
    val mainState = _mainState.asStateFlow()

    init {
        setChallengeStatus(createFakeChallengeStatus())
    }

    fun setChallengeStatus(challengeStatus: ChallengeStatus) {
        _mainState.value = _mainState.value.copy(challengeStatus = challengeStatus)
    }

    fun createFakeChallengeStatus(): ChallengeStatus {
        // 몇 개의 가짜 App 인스턴스 생성
        val fakeApps = listOf(
            ChallengeStatus.App("App1", 30),
            ChallengeStatus.App("App2", 45),
            ChallengeStatus.App("App3", 60)
        )

        // isSuccessList를 랜덤한 Boolean 값으로 채웁니다.
        val fakeIsSuccessList = List(fakeApps.size) { Math.random() < 0.5 }

        // ChallengeStatus 인스턴스 생성
        return ChallengeStatus(
            apps = fakeApps,
            isSuccessList = fakeIsSuccessList,
            goalTime = 120,  // 예시 목표 시간
            period = 7       // 예시 기간 (일)
        )
    }

}

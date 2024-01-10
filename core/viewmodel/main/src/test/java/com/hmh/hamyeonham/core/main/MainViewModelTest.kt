package com.hmh.hamyeonham.core.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.core.MainViewModel
import com.hmh.hamyeonham.usagestats.usecase.GetUsageGoalsUseCase
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var getUsageGoalsUseCase: GetUsageGoalsUseCase
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        getUsageGoalsUseCase = mockk(relaxed = true)
        viewModel = MainViewModel(getUsageGoalsUseCase)
    }

    @Test
    fun `setChallengeStatus가 호출될 때 mainState가 제대로 업데이트 되는지 테스트`() = runTest {
        val testChallengeStatus = ChallengeStatus() // ChallengeStatus의 적절한 초기화 필요
        viewModel.setChallengeStatus(testChallengeStatus)

        val updatedState = viewModel.mainState.first()
        assertEquals(testChallengeStatus, updatedState.challengeStatus)
    }
}

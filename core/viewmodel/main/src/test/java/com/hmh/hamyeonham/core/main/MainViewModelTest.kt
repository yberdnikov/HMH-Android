package com.hmh.hamyeonham.core.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.core.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun `setChallengeStatus가 호출될 때 mainState가 제대로 업데이트 되는지 테스트`() = runTest {
        val viewModel = hiltRule.inject() as MainViewModel
        val testChallengeStatus = ChallengeStatus()

        viewModel.setChallengeStatus(testChallengeStatus)

        val updatedState = viewModel.mainState.first()
        assertEquals(testChallengeStatus, updatedState.challengeStatus)
    }
}

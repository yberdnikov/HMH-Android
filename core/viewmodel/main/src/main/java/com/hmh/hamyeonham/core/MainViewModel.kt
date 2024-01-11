package com.hmh.hamyeonham.core

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.usecase.GetUsageGoalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class MainState(
    val challengeStatus: ChallengeStatus = ChallengeStatus(),
    val usageGoals: List<UsageGoal> = emptyList(),
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsageGoalsUseCase: GetUsageGoalsUseCase,
) : ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    init {
        setUsageGoals(getUsageGoalsUseCase())
    }

    fun setChallengeStatus(challengeStatus: ChallengeStatus) {
        updateState {
            copy(challengeStatus = challengeStatus)
        }
    }

    fun setUsageGoals(usageGoal: List<UsageGoal>) {
        updateState {
            copy(usageGoals = usageGoal)
        }
    }

    fun addUsageGoals(usageGoal: UsageGoal) {
        updateState {
            copy(usageGoals = usageGoals + usageGoal)
        }
    }

    fun updateState(transform: MainState.() -> MainState) {
        val currentState = mainState.value
        val newState = currentState.transform()
        _mainState.value = newState
    }
}

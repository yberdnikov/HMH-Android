package com.hmh.hamyeonham.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.usecase.AddUsageGoalsUseCase
import com.hmh.hamyeonham.usagestats.usecase.GetUsageGoalsUseCase
import com.hmh.hamyeonham.usagestats.usecase.GetUsageStatsListUseCase
import com.hmh.hamyeonham.userinfo.model.UserInfo
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainState(
    val challengeStatus: ChallengeStatus = ChallengeStatus(),
    val usageGoals: List<UsageGoal> = emptyList(),
    val usageStatsList: List<UsageStatAndGoal> = emptyList(),
    val userInfo: UserInfo = UserInfo()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsageGoalsUseCase: GetUsageGoalsUseCase,
    private val getUsageStatsListUseCase: GetUsageStatsListUseCase,
    private val addUsageGoalsUseCase: AddUsageGoalsUseCase,
) : ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    init {
        setUsageGoals(getUsageGoalsUseCase())
        setUsageStatsList()
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

    fun setUserInfo(userInfo: UserInfo) {
        updateState {
            copy(userInfo = userInfo)
        }
    }

    fun addUsageGoals(usageGoal: List<UsageGoal>) {
        // TODO 앱 추가 API
        updateState {
            copy(usageGoals = usageGoals + usageGoal)
        }
    }

    fun updateState(transform: MainState.() -> MainState) {
        val currentState = mainState.value
        val newState = currentState.transform()
        _mainState.value = newState
    }

    private fun setUsageStatsList() {
        val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
        updateState {
            copy(usageStatsList = getUsageStatsListUseCase(startTime, endTime))
        }
    }
}

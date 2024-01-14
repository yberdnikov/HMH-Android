package com.hmh.hamyeonham.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.challenge.repository.ChallengeRepository
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal
import com.hmh.hamyeonham.usagestats.usecase.GetUsageGoalsUseCase
import com.hmh.hamyeonham.usagestats.usecase.GetUsageStatsListUseCase
import com.hmh.hamyeonham.userinfo.model.UserInfo
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val challengeStatus: ChallengeStatus = ChallengeStatus(),
    val usageGoals: List<UsageGoal> = emptyList(),
    val usageStatsList: List<UsageStatusAndGoal> = emptyList(),
    val userInfo: UserInfo = UserInfo(),
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val challengeRepository: ChallengeRepository,
    private val getUsageGoalsUseCase: GetUsageGoalsUseCase,
    private val getUsageStatsListUseCase: GetUsageStatsListUseCase,
    private val userInfoRepository: UserInfoRepository,
) : ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    init {
//        getChallengeStatus()
        getUsageGoal()
        getStatsList()
        getUserInfo()
    }

    fun getUserName(): String = mainState.value.userInfo.name

    private fun getChallengeStatus() {
        viewModelScope.launch {
            challengeRepository.getChallengeData().onSuccess {
                setChallengeStatus(it)
            }.onFailure {
                Log.e("challenge status error", it.toString())
            }
        }
    }

    private fun getUsageGoal() {
        viewModelScope.launch {
            setUsageGaols(getUsageGoalsUseCase())
        }
    }

    private fun getStatsList() {
        val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
        viewModelScope.launch {
            setUsageStatsList(getUsageStatsListUseCase(startTime, endTime))
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            userInfoRepository.getUserInfo().onSuccess {
                setUserInfo(it)
            }.onFailure {
                Log.e("userInfo error", it.toString())
            }
        }
    }

    private fun setUsageGaols(usageGoals: List<UsageGoal>) {
        updateState {
            copy(usageGoals = usageGoals)
        }
    }

    fun setChallengeStatus(challengeStatus: ChallengeStatus) {
        updateState {
            copy(challengeStatus = challengeStatus)
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

    fun updateState(transform: suspend MainState.() -> MainState) {
        viewModelScope.launch {
            val currentState = mainState.value
            val newState = currentState.transform()
            _mainState.value = newState
        }
    }

    private fun setUsageStatsList(usageStatsList: List<UsageStatusAndGoal>) {
        updateState {
            copy(usageStatsList = usageStatsList)
        }
    }
}

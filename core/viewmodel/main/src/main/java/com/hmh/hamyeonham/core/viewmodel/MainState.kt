package com.hmh.hamyeonham.core.viewmodel

import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.common.time.getCurrentDateOfDefaultTimeZone
import com.hmh.hamyeonham.common.time.minusDaysFromDate
import com.hmh.hamyeonham.core.domain.usagegoal.model.UsageGoal
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal
import kotlinx.datetime.LocalDate

data class MainState(
    val appGoals: List<ChallengeStatus.AppGoal> = emptyList(),
    val challengeStatusList: List<ChallengeStatus.Status> = emptyList(),
    val totalGoalTimeInHour: Int = 0,
    val period: Int = 0,
    val todayIndex: Int = 0,
    val usageGoals: List<UsageGoal> = emptyList(),
    val usageStatusAndGoals: List<UsageStatusAndGoal> = emptyList(),
    val name: String = "",
    val point: Int = 0,
    val challengeSuccess: Boolean = true,
) {
    val startDate: LocalDate = minusDaysFromDate(getCurrentDateOfDefaultTimeZone(), todayIndex - 1)
    val isChallengeExist: Boolean = todayIndex != -1
    val todayIndexAsDate: Int = todayIndex + 1
}
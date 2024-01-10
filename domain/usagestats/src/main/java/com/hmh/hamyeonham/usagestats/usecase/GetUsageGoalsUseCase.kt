package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import javax.inject.Inject

class GetUsageGoalsUseCase @Inject constructor(
    private val getUsageGoalsUseCase: UsageGoalsRepository
) {
    operator fun invoke(): List<UsageGoal> {
        return getUsageGoalsUseCase.getUsageGoals()
    }
}

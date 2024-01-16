package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsageGoalsUseCase @Inject constructor(
    private val usageGoalsRepository: UsageGoalsRepository,
) {
    suspend operator fun invoke(): Flow<List<UsageGoal>> {
        return usageGoalsRepository.getUsageGoals()
    }
}

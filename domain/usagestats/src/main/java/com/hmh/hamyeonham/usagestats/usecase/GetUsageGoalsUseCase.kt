//package com.hmh.hamyeonham.usagestats.usecase
//
//import com.hmh.hamyeonham.usagestats.model.UsageGoal
//import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
//import javax.inject.Inject
//
//class GetUsageGoalsUseCase @Inject constructor(
//    private val usageGoalsRepository: UsageGoalsRepository,
//) {
//    suspend operator fun invoke(): List<UsageGoal> {
//        return getUsageGoals()
//    }
//
//    suspend fun getUsageGoals(): List<UsageGoal> {
//        return usageGoalsRepository.getUsageGoals()
//    }
//
//    suspend fun getUsageGoalTime(packageName: String): Long {
//        return usageGoalsRepository.getUsageGoals()
//            .firstOrNull { it.packageName == packageName }?.goalTime ?: 0
//    }
//}

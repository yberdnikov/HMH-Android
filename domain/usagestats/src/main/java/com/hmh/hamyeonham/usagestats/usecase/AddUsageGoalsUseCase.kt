package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import javax.inject.Inject

class AddUsageGoalsUseCase @Inject constructor(
    private val usageGoalsRepository: UsageGoalsRepository,
)

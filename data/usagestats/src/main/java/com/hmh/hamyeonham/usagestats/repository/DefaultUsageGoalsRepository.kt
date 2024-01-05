package com.hmh.hamyeonham.usagestats.repository

import android.util.Log
import com.hmh.hamyeonham.usagestats.datasource.UsageGoalsDataSource
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import javax.inject.Inject

class DefaultUsageGoalsRepository
    @Inject
    constructor(
        private val usageGoalsDataSource: UsageGoalsDataSource,
    ) : UsageGoalsRepository {
        override fun getUsageGoalsForApps(): List<UsageGoal> {
            val usageGoals =
                usageGoalsDataSource.getUsageGoals().drop(1).map {
                    UsageGoal(it.packageName, it.goalTime)
                }
            printList(usageGoals)
            return usageGoals
        }

        override fun getTotalUsageGoal(): UsageGoal {
            val usageGoalModel = usageGoalsDataSource.getUsageGoals()[0]
            return UsageGoal(usageGoalModel.packageName, usageGoalModel.goalTime)
        }

        override fun getUsageGoalTime(packageName: String): Long {
            return usageGoalsDataSource.getUsageGoals()
                .firstOrNull { it.packageName == packageName }?.goalTime ?: 0
        }

        private fun printList(list: List<UsageGoal>) {
            Log.d("class name", "dugr")
            for (i in list) {
                Log.d("package name", i.packageName)
            }
        }
    }

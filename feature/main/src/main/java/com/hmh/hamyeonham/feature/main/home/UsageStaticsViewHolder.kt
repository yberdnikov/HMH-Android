package com.hmh.hamyeonham.feature.main.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.concatDifferentColored남음AndBindText
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.view.initAndStartProgressBarAnimation
import com.hmh.hamyeonham.feature.main.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsViewHolder(
    private val binding: ItemUsagestaticBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        binding.run {
            tvAppname.text = context.getAppNameFromPackageName(usageStatAndGoal.packageName)
            ivAppicon.setImageDrawable(
                context.getAppIconFromPackageName(
                    usageStatAndGoal.packageName,
                ),
            )
            tvAppGoalTime.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbAppUsage.progress = usageStatAndGoal.usedPercentage
        }
        context.concatDifferentColored남음AndBindText(
            convertTimeToString(usageStatAndGoal.goalTime),
            binding.tvAppTimeLeft,
            com.hmh.hamyeonham.core.designsystem.R.color.gray1,
        )
        initAndStartProgressBarAnimation(binding.pbAppUsage, usageStatAndGoal.usedPercentage)
    }
}

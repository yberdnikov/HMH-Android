package com.hmh.hamyeonham.statistics

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.time.getLeftTimeInString
import com.hmh.hamyeonham.common.time.getUsedPercentage
import com.hmh.hamyeonham.feature.statistics.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsViewHolder(
    private val binding: ItemUsagestaticBinding,
    private val context: Context,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        binding.tvItemusagestatAppname.text =
            context.getAppNameFromPackageName(usageStatAndGoal.packageName)
        binding.tvItemusagestatLeftHour.text =
            getLeftTimeInString(usageStatAndGoal.totalTimeInForeground, usageStatAndGoal.goalTime)
        binding.ivItemusagestatAppicon.setImageDrawable(
            context.getAppIconFromPackageName(
                usageStatAndGoal.packageName,
            ),
        )
        binding.pbItemUsagestat.setProgress(
            getUsedPercentage(
                usageStatAndGoal.totalTimeInForeground,
                usageStatAndGoal.goalTime,
            ),
        )
    }
}

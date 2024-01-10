package com.hmh.hamyeonham.feature.main.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.concatDifferentColored남음AndBindText
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.view.initAndStartProgressBarAnimation
import com.hmh.hamyeonham.feature.main.databinding.ItemUsagestaticTotalBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsTotalViewHolder(
    private val binding: ItemUsagestaticTotalBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        binding.run {
            tvTotalGoal.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbTotalUsage.progress = usageStatAndGoal.usedPercentage
        }
        context.concatDifferentColored남음AndBindText(
            convertTimeToString(usageStatAndGoal.goalTime),
            binding.tvTotalTimeLeft,
            com.hmh.hamyeonham.core.designsystem.R.color.gray1,
        )
        initAndStartProgressBarAnimation(
            binding.pbTotalUsage,
            usageStatAndGoal.usedPercentage,
        )
    }
}

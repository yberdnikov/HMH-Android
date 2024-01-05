package com.hmh.hamyeonham.statistics

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.feature.statistics.databinding.ItemUsagestaticTotalBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsTotalViewHolder(
    private val binding: ItemUsagestaticTotalBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        binding.run {
            tvStaticsHour.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbStatics.progress = usageStatAndGoal.usedPercentage
            tvStaticsTimeLeft.text = convertTimeToString(usageStatAndGoal.timeLeft)
        }
        Log.d("binding", usageStatAndGoal.packageName)
    }
}

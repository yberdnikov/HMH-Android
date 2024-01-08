package com.hmh.hamyeonham.statistics

import android.animation.ObjectAnimator
import android.content.Context
import android.view.animation.AccelerateInterpolator
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
            tvItemusagestaticTotalHour.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbItemusagestaticTotal.progress = usageStatAndGoal.usedPercentage
            tvItemusagestaticTotalTimeLeft.text = convertTimeToString(usageStatAndGoal.timeLeft)
        }
        startProgressAnimation(usageStatAndGoal.usedPercentage)
    }

    private fun startProgressAnimation(progressTo: Int) {
        ObjectAnimator.ofInt(binding.pbItemusagestaticTotal, "progress", 0, progressTo).apply {
            interpolator = AccelerateInterpolator()
            start()
        }
    }

    companion object {
        private const val LEFT = "남음"
    }
}

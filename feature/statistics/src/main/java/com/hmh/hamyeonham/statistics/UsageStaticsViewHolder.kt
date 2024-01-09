package com.hmh.hamyeonham.statistics

import android.animation.ObjectAnimator
import android.content.Context
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.feature.statistics.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsViewHolder(
    private val binding: ItemUsagestaticBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        bindAttributes(usageStatAndGoal)
        initbindAndAnimateProgress(usageStatAndGoal.usedPercentage)
    }

    private fun initbindAndAnimateProgress(progressTo: Int) {
        val objectAnimator =
            ObjectAnimator.ofInt(binding.pbItemUsagestat, "progress", 0, progressTo).apply {
                interpolator = AccelerateInterpolator()
            }
        objectAnimator.start()
    }

    private fun bindAttributes(usageStatAndGoal: UsageStatAndGoal) {
        binding.run {
            tvItemusagestatAppname.text =
                context.getAppNameFromPackageName(usageStatAndGoal.packageName)
            ivItemusagestatAppicon.setImageDrawable(
                context.getAppIconFromPackageName(
                    usageStatAndGoal.packageName,
                ),
            )
            tvItemusagestatTimeLeft.text = convertTimeToString(usageStatAndGoal.timeLeft)
            tvItemusagestatGoalTime.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbItemUsagestat.progress = usageStatAndGoal.usedPercentage
        }
    }
}

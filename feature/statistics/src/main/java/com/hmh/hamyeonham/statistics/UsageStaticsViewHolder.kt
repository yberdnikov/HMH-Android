package com.hmh.hamyeonham.statistics

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.view.initAndStartProgressBarAnimation
import com.hmh.hamyeonham.feature.statistics.R
import com.hmh.hamyeonham.feature.statistics.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsViewHolder(
    private val binding: ItemUsagestaticBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        binding.run {
            tvItemusagestatAppname.text =
                context.getAppNameFromPackageName(usageStatAndGoal.packageName)
            ivItemusagestatAppicon.setImageDrawable(
                context.getAppIconFromPackageName(
                    usageStatAndGoal.packageName,
                ),
            )
            tvItemusagestatGoalTime.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbItemUsagestat.progress = usageStatAndGoal.usedPercentage
        }
        bindTotalLeftWithSpannable(usageStatAndGoal)
        initAndStartProgressBarAnimation(binding.pbItemUsagestat, usageStatAndGoal.usedPercentage)
    }

    fun bindTotalLeftWithSpannable(usageStatAndGoal: UsageStatAndGoal) {
        val builder = SpannableStringBuilder(
            convertTimeToString(usageStatAndGoal.timeLeft) + " " + ContextCompat.getString(
                context,
                R.string.all_left,
            ),
        )
        builder.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    com.hmh.hamyeonham.core.designsystem.R.color.gray2,
                ),
            ),
            builder.length - 2,
            builder.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE,
        )
        binding.tvItemusagestatTimeLeft.text = builder
    }
}

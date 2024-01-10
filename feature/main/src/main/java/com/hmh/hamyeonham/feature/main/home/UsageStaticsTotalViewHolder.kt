package com.hmh.hamyeonham.feature.main.home

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.view.initAndStartProgressBarAnimation
import com.hmh.hamyeonham.feature.main.R
import com.hmh.hamyeonham.feature.main.databinding.ItemUsagestaticTotalBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal

class UsageStaticsTotalViewHolder(
    private val binding: ItemUsagestaticTotalBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatAndGoal: UsageStatAndGoal) {
        binding.run {
            tvItemusagestaticTotalHour.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbItemusagestaticTotal.progress = usageStatAndGoal.usedPercentage
        }
        bindTotalLeftWithSpannable(usageStatAndGoal)
        initAndStartProgressBarAnimation(
            binding.pbItemusagestaticTotal,
            usageStatAndGoal.usedPercentage,
        )
    }

    fun bindTotalLeftWithSpannable(usageStatAndGoal: UsageStatAndGoal) {
        val builder = SpannableStringBuilder(
            convertTimeToString(usageStatAndGoal.timeLeft) + " " + getString(
                context,
                R.string.all_left,
            ),
        )
        builder.setSpan(
            ForegroundColorSpan(
                getColor(
                    context,
                    com.hmh.hamyeonham.core.designsystem.R.color.gray1,
                ),
            ),
            builder.length - 2,
            builder.length,
            SPAN_INCLUSIVE_EXCLUSIVE,
        )
        binding.tvItemusagestaticTotalTimeLeft.text = builder
    }
}

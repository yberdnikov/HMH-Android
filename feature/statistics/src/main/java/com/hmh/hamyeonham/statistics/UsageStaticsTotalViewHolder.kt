package com.hmh.hamyeonham.statistics

import android.animation.ObjectAnimator
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat.getColor
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
            tvStaticsTotalHour.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbStaticsTotal.progress = usageStatAndGoal.usedPercentage
            tvStaticsTotalTimeLeft.text = convertTimeToString(usageStatAndGoal.timeLeft)
//            setSpannable(usageStatAndGoal)
        }
        startProgressAnimation(usageStatAndGoal.usedPercentage)
    }

    private fun setSpannable(usageStatAndGoal: UsageStatAndGoal) {
        val builder = SpannableStringBuilder(
            convertTimeToString(usageStatAndGoal.timeLeft) + " " + LEFT,
        )
        // LEFT 스타일 적용
        // size 11sp
//        builder.setSpan(
//            AbsoluteSizeSpan(11),
//            builder.length - 2,
//            builder.length,
//            SPAN_INCLUSIVE_EXCLUSIVE,
//        )
        // color gray1
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
        binding.tvStaticsTotalTimeLeft.text = builder
    }

    private fun startProgressAnimation(progressTo: Int) {
        ObjectAnimator.ofInt(binding.pbStaticsTotal, "progress", 0, progressTo).apply {
            interpolator = AccelerateInterpolator()
            start()
        }
    }

    companion object {
        private const val LEFT = "남음"
    }
}

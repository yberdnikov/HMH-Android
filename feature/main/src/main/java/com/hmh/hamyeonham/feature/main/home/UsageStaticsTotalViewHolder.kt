package com.hmh.hamyeonham.feature.main.home

import android.content.Context
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.colorSecondStrAndBindText
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
            tvTotalGoal.text = convertTimeToString(usageStatAndGoal.goalTime)
            pbTotalUsage.progress = usageStatAndGoal.usedPercentage
        }

        context.colorSecondStrAndBindText(
            convertTimeToString(usageStatAndGoal.goalTime),
            getString(
                context,
                R.string.all_left,
            ),
            binding.tvTotalTimeLeft,
            com.hmh.hamyeonham.core.designsystem.R.color.gray1,
        )
        initAndStartProgressBarAnimation(
            binding.pbTotalUsage,
            usageStatAndGoal.usedPercentage,
        )
    }

//    fun attach() {
//        val videoUri = Uri.parse("android.resource://${context.packageName}/raw/vd_blackhole1")
//        binding.vvBlackhole.run {
//            setVideoURI(videoUri)
// //            vvBlackhole.setBackgroundColor(getColo)
//            start()
// //            vvBlackhole.setOnCompletionListener {
// //                vvBlackhole.start()
// //            }
//            setOnCompletionListener {
//                start()
//            }
//        }
//    }

//    fun detach() {
//        binding.vvBlackhole.stopPlayback()
//    }
}

package com.hmh.hamyeonham.feature.main.home

import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.colorSecondStrAndBindText
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.view.initAndStartProgressBarAnimation
import com.hmh.hamyeonham.feature.main.R
import com.hmh.hamyeonham.feature.main.databinding.ItemUsagestaticTotalBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal

class UsageStaticsTotalViewHolder(
    private val binding: ItemUsagestaticTotalBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatusAndGoal: UsageStatusAndGoal) {
        binding.run {
            tvTotalGoal.text = convertTimeToString(usageStatusAndGoal.goalTime)
            pbTotalUsage.progress = usageStatusAndGoal.usedPercentage
        }
        context.colorSecondStrAndBindText(
            convertTimeToString(usageStatusAndGoal.timeLeft),
            getString(
                context,
                R.string.all_left,
            ),
            binding.tvTotalTimeLeft,
            com.hmh.hamyeonham.core.designsystem.R.color.gray1,
        )
        initAndStartProgressBarAnimation(
            binding.pbTotalUsage,
            usageStatusAndGoal.usedPercentage,
        )
        setBlackholeLoop()
    }

    fun bindBlackhole(usageStatusAndGoal: UsageStatusAndGoal) {
        bindBlackholeVideo(usageStatusAndGoal.getBlackholeUri())
        bindBlackholeDescription(usageStatusAndGoal.getBlackholeDescription())
    }

    private fun setBlackholeLoop() {
        binding.vvBlackhole.setOnCompletionListener {
            binding.vvBlackhole.start()
        }
    }

    private fun bindBlackholeVideo(uri: String) {
        val uri =
            Uri.parse(uri)
        binding.vvBlackhole.run {
            setVideoURI(uri)
            requestFocus()
            start()
        }
    }

    private fun bindBlackholeDescription(description: String) {
        binding.tvBlackholeDescription.text = description
    }

    fun resume(usageStatusAndGoal: UsageStatusAndGoal) {
        bindBlackhole(usageStatusAndGoal)
    }

    fun pause() {
    }
}

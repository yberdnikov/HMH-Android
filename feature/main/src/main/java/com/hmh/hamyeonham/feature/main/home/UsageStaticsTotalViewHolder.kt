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
import com.hmh.hamyeonham.usagestats.model.Blackhole
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal
import com.hmh.hamyeonham.userinfo.model.UserInfo

class UsageStaticsTotalViewHolder(
    private val binding: ItemUsagestaticTotalBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStatusAndGoal: UsageStatusAndGoal, userInfo: UserInfo?) {
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
        bindBlackhole(usageStatusAndGoal, userInfo)
        setBlackholeLoop()
    }

    fun bindBlackhole(usageStatusAndGoal: UsageStatusAndGoal, userInfo: UserInfo?) {
        bindBlackholeVideo(usageStatusAndGoal.getBlackholeUri())
        bindBlackholeDescription(usageStatusAndGoal, userInfo)
    }

    private fun setBlackholeLoop() {
        binding.vvBlackhole.setOnCompletionListener {
            binding.vvBlackhole.start()
        }
    }

    private fun bindBlackholeVideo(uri: String) {
        val uri = Uri.parse(uri)
        binding.vvBlackhole.run {
            setVideoURI(uri)
            requestFocus()
            setOnPreparedListener {
                it.start()
            }
        }
    }

    private fun bindBlackholeDescription(
        userStatusAndGoal: UsageStatusAndGoal,
        userInfo: UserInfo?,
    ) {
        binding.tvBlackholeDescription.text = when (userStatusAndGoal.blackhole) {
            Blackhole.FIRST -> userInfo?.name ?: ""
            else -> ""
        } + userStatusAndGoal.getBlackholeDescription()
    }

    fun pause() {
    }
}

package com.hmh.hamyeonham.statistics

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.feature.statistics.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStat
import java.util.concurrent.TimeUnit

class UsageStaticsViewHolder(
    private val binding: ItemUsagestaticBinding,
    private val context: Context,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStat: UsageStat) {
        binding.tvItemusagestatAppname.text =
            context.getAppNameFromPackageName(usageStat.packageName)
        binding.tvItemusagestatLeftHour.text = convertMsToMin(usageStat.totalTimeInForeground).toString()
        binding.ivItemusagestatAppicon.setImageDrawable(context.getAppIconFromPackageName(usageStat.packageName))
        binding.pbItemUsagestat.setProgress(getUsedPercentage(usageStat.totalTimeInForeground))
    }

    private fun getUsedPercentage(usedTime: Long): Int {
        val usedMin = convertMsToMin(usedTime)
        val goal = 100
        Log.d("usedMin", usedMin.toString())
        Log.d("return", (usedMin * 100 / goal).toInt().toString())
        return (usedMin * 100 / goal).toInt()
    }

    private fun convertMsToMin(ms: Long) = TimeUnit.MILLISECONDS.toSeconds(ms)
}

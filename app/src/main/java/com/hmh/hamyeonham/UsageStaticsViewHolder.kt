package com.hmh.hamyeonham

import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStat

class UsageStaticsViewHolder(private val binding: ItemUsagestaticBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(usageStat: UsageStat) {
        binding.tvItemusagestatAppname.text = getAppNameFromPackageName(usageStat.packageName)
        binding.tvItemusagestatTimeleft.text = getTimeLeft(usageStat)
        binding.ivItemusagestatAppicon.setImageResource(getAppIconFromPackageName(usageStat.packageName))
        binding.pbItemusagestat.progress = usageStat.usedPercentage
    }
}

package com.hmh.hamyeonham.presentation.main

import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.databinding.ItemUsagestatBinding
import com.hmh.hamyeonham.domain.AppUsage

class UsagestatViewHolder(private val binding: ItemUsagestatBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(appUsage: AppUsage) {
        binding.tvItemusagestatAppname.text = appUsage.appName
        binding.tvItemusagestatTimeleft.text = appUsage.timeLeft.toString()
        binding.ivItemusagestatAppicon.setImageResource(appUsage.appIcon)
    }
}

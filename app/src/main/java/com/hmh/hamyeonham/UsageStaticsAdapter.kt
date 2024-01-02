package com.hmh.hamyeonham

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStat

class UsageStaticsAdapter (private val appUsageList: List<UsageStat>) : RecyclerView.Adapter<UsageStaticsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UsageStaticsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UsageStaticsViewHolder(ItemUsagestaticBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(
        holder: UsageStaticsViewHolder,
        position: Int,
    ) {
        holder.onBind(appUsageList[position])
    }

    override fun getItemCount(): Int = appUsageList.size
})
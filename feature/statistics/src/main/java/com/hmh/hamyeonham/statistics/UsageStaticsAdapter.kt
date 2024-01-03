package com.hmh.hamyeonham.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hmh.hamyeonham.common.view.ItemDiffCallback
import com.hmh.hamyeonham.feature.statistics.databinding.ItemUsagestaticBinding
import com.hmh.hamyeonham.usagestats.model.UsageStat

class UsageStaticsAdapter : ListAdapter<UsageStat, UsageStaticsViewHolder>(
    ItemDiffCallback<UsageStat>(
        onItemsTheSame = { old, new -> old.packageName == new.packageName },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UsageStaticsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUsagestaticBinding.inflate(inflater, parent, false)
        return UsageStaticsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(
        holder: UsageStaticsViewHolder,
        position: Int,
    ) {
        holder.onBind(currentList[position])
    }
}

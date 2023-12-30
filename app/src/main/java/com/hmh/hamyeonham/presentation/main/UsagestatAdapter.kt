package com.hmh.hamyeonham.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.hmh.hamyeonham.databinding.ItemUsagestatBinding
import com.hmh.hamyeonham.domain.AppUsage

class UsagestatAdapter(private val appUsageList: List<AppUsage>) : Adapter<UsagestatViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UsagestatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UsagestatViewHolder(ItemUsagestatBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(
        holder: UsagestatViewHolder,
        position: Int,
    ) {
        holder.onBind(appUsageList[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

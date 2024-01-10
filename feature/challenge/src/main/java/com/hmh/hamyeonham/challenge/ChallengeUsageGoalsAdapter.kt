package com.hmh.hamyeonham.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.view.ItemDiffCallback
import com.hmh.hamyeonham.feature.challenge.databinding.ItemChallengeUsageGoalBinding
import com.hmh.hamyeonham.usagestats.model.UsageGoal

class ChallengeUsageGoalsAdapter :
    ListAdapter<UsageGoal, ChallengeUsageGoalsAdapter.ChallengeUsageGoalsViewHolder>(
        ItemDiffCallback(
            onItemsTheSame = { oldItem, newItem ->
                oldItem.packageName == newItem.packageName
            },
            onContentsTheSame = { oldItem, newItem ->
                oldItem == newItem
            }
        )
    ) {
    class ChallengeUsageGoalsViewHolder(private val binding: ItemChallengeUsageGoalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UsageGoal) {
            binding.run {
                val context = root.context
                val packageName = item.packageName
                val appName = context.getAppNameFromPackageName(packageName)
                ivAppIcon.setImageDrawable(context.getAppIconFromPackageName(packageName))
                tvAppName.text = appName
                tvAppUsageGoal.text = item.formattedGoalTime
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChallengeUsageGoalsViewHolder {
        return ChallengeUsageGoalsViewHolder(
            ItemChallengeUsageGoalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChallengeUsageGoalsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

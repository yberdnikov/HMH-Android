package com.hmh.hamyeonham.challenge.appselection

import android.content.pm.ResolveInfo
import androidx.recyclerview.widget.ListAdapter
import com.hmh.hamyeonham.challenge.ChallengeUsageGoalsAdapter
import com.hmh.hamyeonham.common.view.ItemDiffCallback
import com.hmh.hamyeonham.usagestats.model.UsageGoal

class AppselectionAdapter :
    ListAdapter<List<ResolveInfo>, >(
        ItemDiffCallback(onItemsTheSame = { oldItem, newItem ->
            oldItem.packageName == newItem.packageName
        }, onContentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        })
    )

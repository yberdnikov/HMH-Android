package com.hmh.hamyeonham.challenge.appselection

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.feature.challenge.databinding.ItemAppBinding

class AppselectionViewHolder(private val binding: ItemAppBinding, private val context: Context) :
    ViewHolder(binding.root) {
    fun onBind(packageName: String) {
        binding.tvAppname.text = context.getAppNameFromPackageName(packageName)
        binding.ivAppicon.setImageDrawable(context.getAppIconFromPackageName(packageName))
    }
}

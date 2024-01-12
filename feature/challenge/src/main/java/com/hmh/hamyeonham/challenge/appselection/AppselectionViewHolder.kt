package com.hmh.hamyeonham.challenge.appselection

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.feature.challenge.databinding.ItemAppBinding

class AppselectionViewHolder(
    private val binding: ItemAppBinding,
    private val onAppCheckboxClicked: (Int) -> Unit,
    private val onAppCheckboxUnClicked: (Int) -> Unit
) : ViewHolder(binding.root) {
    private val context = binding.root.context
    fun onBind(packageName: String) {
        binding.tvAppname.text = context.getAppNameFromPackageName(packageName)
        binding.ivAppicon.setImageDrawable(context.getAppIconFromPackageName(packageName))
        setCheckBoxButtonListener()
    }

    private fun setCheckBoxButtonListener() {
        binding.cbApp.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                if (binding.cbApp.isChecked) {
                    onAppCheckboxClicked(position)
                } else {
                    onAppCheckboxUnClicked(position)
                }
            }
        }
    }
}

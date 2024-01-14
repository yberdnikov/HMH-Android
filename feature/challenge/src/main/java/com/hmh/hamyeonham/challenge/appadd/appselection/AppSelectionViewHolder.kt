package com.hmh.hamyeonham.challenge.appadd.appselection

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.feature.challenge.databinding.ItemAppBinding

class AppSelectionViewHolder(
    private val binding: ItemAppBinding,
    private val onAppCheckboxClicked: (String) -> Unit,
    private val onAppCheckboxUnClicked: (String) -> Unit
) : ViewHolder(binding.root) {
    fun onBind(packageName: String) {
        binding.run {
            val context = binding.root.context
            tvAppname.text = context.getAppNameFromPackageName(packageName)
            ivAppicon.setImageDrawable(context.getAppIconFromPackageName(packageName))
        }
        setCheckBoxButtonListener(packageName)
    }

    private fun setCheckBoxButtonListener(packageName: String) {
        binding.cbApp.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                if (binding.cbApp.isChecked) {
                    onAppCheckboxClicked(packageName)
                } else {
                    onAppCheckboxUnClicked(packageName)
                }
            }
        }
    }
}

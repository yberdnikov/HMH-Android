package com.hmh.hamyeonham.challenge.appselection

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.feature.challenge.databinding.ItemAppBinding

class AppselectionViewHolder(
    private val binding: ItemAppBinding,
    private val context: Context,
    private val onAppCheckboxClicked: (Int) -> Unit
) :
    ViewHolder(binding.root) {
    fun onBind(packageName: String) {
        binding.tvAppname.text = context.getAppNameFromPackageName(packageName)
        binding.ivAppicon.setImageDrawable(context.getAppIconFromPackageName(packageName))
        initCheckBoxColor()
        setCheckBoxButtonListener()
    }

    private fun initCheckBoxColor() {
        setColor(com.hmh.hamyeonham.core.designsystem.R.color.white_text)
    }

    private fun setCheckBoxButtonListener() {
        binding.cbApp.setOnClickListener {
            if (binding.cbApp.isChecked) {
                setColor(com.hmh.hamyeonham.core.designsystem.R.color.blue_purple_text)
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onAppCheckboxClicked(position)
                }
            } else {
                setColor(com.hmh.hamyeonham.core.designsystem.R.color.white_text)
            }
        }
    }

    private fun setColor(color: Int) {
        val colorStateList = ColorStateList.valueOf(
            getColor(
                context,
                color
            )
        )
        binding.cbApp.buttonTintList = colorStateList
    }
}

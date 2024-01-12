package com.hmh.hamyeonham.challenge.appselection

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hmh.hamyeonham.common.context.getAppIconFromPackageName
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.feature.challenge.databinding.ItemAppBinding

class AppselectionViewHolder(private val binding: ItemAppBinding, private val context: Context) :
    ViewHolder(binding.root) {
    fun onBind(packageName: String) {
        binding.tvAppname.text = context.getAppNameFromPackageName(packageName)
        binding.ivAppicon.setImageDrawable(context.getAppIconFromPackageName(packageName))
        initCheckBoxColor()
        setCheckBoxButtonListener()
    }
    private fun initCheckBoxColor() {
        val colorStateList = ColorStateList.valueOf(
            getColor(
                context,
                com.hmh.hamyeonham.core.designsystem.R.color.white_text
            )
        )
        binding.cbApp.buttonTintList = colorStateList
    }

    private fun setCheckBoxButtonListener() {
        binding.cbApp.setOnClickListener {
            if (binding.cbApp.isChecked) {
                val colorStateList = ColorStateList.valueOf(
                    getColor(
                        context,
                        com.hmh.hamyeonham.core.designsystem.R.color.blue_purple_text
                    )
                )
                binding.cbApp.buttonTintList = colorStateList
            } else {
                val colorStateList = ColorStateList.valueOf(
                    getColor(
                        context,
                        com.hmh.hamyeonham.core.designsystem.R.color.white_text
                    )
                )
                binding.cbApp.buttonTintList = colorStateList
            }
        }
    }
}

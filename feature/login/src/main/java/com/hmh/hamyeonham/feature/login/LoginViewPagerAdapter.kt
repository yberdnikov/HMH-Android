package com.hmh.hamyeonham.feature.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hmh.hamyeonham.feature.login.model.LoginViewImageList
import com.hmh.hamyeonham.feature.login.databinding.ItemLoginViewPagerBinding

class LoginViewPagerAdapter(private val imageList: List<LoginViewImageList>) :
    RecyclerView.Adapter<LoginViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_login_view_pager, parent, false)
        return PagerViewHolder(ItemLoginViewPagerBinding.bind(view))
    }

    class PagerViewHolder(private val binding: ItemLoginViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(imageInfo: LoginViewImageList) {
            binding.run {
                ivLoginViewPagerItem.load(imageInfo.viewPagerImage) {
                    placeholder(R.drawable.login_sample_rectagle_viewpager)
                    error(R.drawable.login_sample_rectagle_viewpager)
                }
            }
        }
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val exploreImage = imageList[position]
        holder.onBindView(exploreImage)
    }
}

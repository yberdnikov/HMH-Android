package com.hmh.hamyeonham.challenge.appadd

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.challenge.appadd.appselection.AppSelectionViewModel
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppAddBinding

class AppAddActivity : AppCompatActivity() {
    companion object {
        const val SELECTED_APPS = "selected_apps"
    }

    private val binding by viewBinding(ActivityAppAddBinding::inflate)
    private val viewModel by viewModels<AppSelectionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        binding.vpAppAdd.adapter = AppAddViewPagerAdapter(this)
    }
}

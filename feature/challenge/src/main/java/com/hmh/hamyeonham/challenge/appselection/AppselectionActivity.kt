package com.hmh.hamyeonham.challenge.appselection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppselectionBinding

class AppselectionActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityAppselectionBinding::inflate)
    private val appList: List<String> by lazy {
        getInstalledApps()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initAppSelectionRecyclerAdapter()
        collectAndSubmitApplist()
    }

    private fun getInstalledApps(): List<String> {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfoList = packageManager.queryIntentActivities(intent, 0)
        return resolveInfoList.map {
            it.activityInfo.packageName
        }
    }

    private fun initAppSelectionRecyclerAdapter() {
        binding.rvAppselection.run {
            adapter = AppselectionAdapter(
                onAppCheckboxClicked = {
                    binding.btAppselection.apply {
                        isEnabled = true
                        isSelected = true
                    }
                }
            )
            layoutManager = LinearLayoutManager(this@AppselectionActivity)
            val usageStaticsAdapter = binding.rvAppselection.adapter as? AppselectionAdapter
            usageStaticsAdapter?.submitList(appList)
        }
    }

    private fun collectAndSubmitApplist() {
//        val appList = getInstalledApps()
        val usageStaticsAdapter = binding.rvAppselection.adapter as? AppselectionAdapter
        for (i in appList)
            Log.d("app name", i)
        usageStaticsAdapter?.submitList(appList)
    }
}

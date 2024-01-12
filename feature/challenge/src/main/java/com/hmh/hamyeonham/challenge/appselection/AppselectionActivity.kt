package com.hmh.hamyeonham.challenge.appselection

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppselectionBinding

class AppselectionActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityAppselectionBinding::inflate)
    private val appList: List<String> by lazy {
        getInstalledApps()
    }
    private val appSelectionViewModel by viewModels<AppselectionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initAppSelectionRecyclerAdapter()
        initAppSelectionButtonListener()
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
                    onAppCheckboxClicked(it)
                },
                onAppCheckboxUnClicked = {
                    onAppCheckboxUnClicked(it)
                }
            )
            layoutManager = LinearLayoutManager(this@AppselectionActivity)
            val usageStaticsAdapter = binding.rvAppselection.adapter as? AppselectionAdapter
            usageStaticsAdapter?.submitList(appList)
        }
    }

    private fun collectAndSubmitApplist() {
        val usageStaticsAdapter = binding.rvAppselection.adapter as? AppselectionAdapter
        usageStaticsAdapter?.submitList(appList)
    }

    private fun initAppSelectionButtonListener() {
        enableSelectButton(false)
        binding.btAppselection.setOnClickListener {
            val intent = Intent(this, AppselectionActivity::class.java)
            intent.putExtra("selectedApps", appSelectionViewModel.selectedApp.toTypedArray())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun onAppCheckboxClicked(position: Int) {
        appSelectionViewModel.selectedApp.add(appList[position])
        enableSelectButton(true)
    }

    private fun onAppCheckboxUnClicked(position: Int) {
        appSelectionViewModel.selectedApp.remove(appList[position])
        if (appSelectionViewModel.nonSelected()) {
            enableSelectButton(false)
        }
    }

    private fun enableSelectButton(appSelectButtonEnable: Boolean) {
        binding.btAppselection.apply {
            isEnabled = appSelectButtonEnable
            isSelected = appSelectButtonEnable
        }
    }
}

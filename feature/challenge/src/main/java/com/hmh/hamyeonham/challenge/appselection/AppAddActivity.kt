package com.hmh.hamyeonham.challenge.appselection

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppAddBinding

class AppAddActivity : AppCompatActivity() {
    companion object {
        const val SELECTED_APPS = "selected_apps"
    }

    private val binding by viewBinding(ActivityAppAddBinding::inflate)
    private val appSelectionViewModel by viewModels<AppSelectionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
        initAppSelectionRecyclerAdapter()
        initAppSelectionButtonListener()
    }

    private fun initViewPager() {

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
            adapter = AppSelectionAdapter(
                onAppCheckboxClicked = {
                    onAppCheckboxClicked(it)
                },
                onAppCheckboxUnClicked = {
                    onAppCheckboxUnClicked(it)
                }
            )
            layoutManager = LinearLayoutManager(this@AppAddActivity)
            val usageStaticsAdapter = binding.rvAppselection.adapter as? AppSelectionAdapter
            usageStaticsAdapter?.submitList(getInstalledApps())
        }
    }

    private fun initAppSelectionButtonListener() {
        binding.btAppselection.setOnClickListener {
            val intent = Intent(this, AppAddActivity::class.java)
            intent.putExtra(SELECTED_APPS, appSelectionViewModel.selectedApp.toTypedArray())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun onAppCheckboxClicked(packageName: String) {
        appSelectionViewModel.selectedApp.add(packageName)
        enableSelectButton(true)
    }

    private fun onAppCheckboxUnClicked(packageName: String) {
        appSelectionViewModel.selectedApp.remove(packageName)
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

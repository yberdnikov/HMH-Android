package com.hmh.hamyeonham.feature.statistics

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class StaticsActivity : AppCompatActivity() {
    private val staticsViewModel by viewModels<StaticsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statics)
//        collectUsageStatsList()
    }

//    private fun collectUsageStatsList() {
//        lifecycleScope.launch {
//            staticsViewModel.usageStatsList.collect {
//                it.forEach {
//                    Log.d(
//                        "MainActivity",
//                        "packageName: ${getAppNameFromPackageName(it.packageName)}",
//                    )
//                    Log.d("MainActivity", "totalTimeInForeground: ${it.totalTimeInForeground}")
//                }
//            }
//        }
//    }
}

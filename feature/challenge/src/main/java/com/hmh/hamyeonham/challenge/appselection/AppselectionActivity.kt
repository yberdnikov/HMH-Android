package com.hmh.hamyeonham.challenge.appselection

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.ActivityAppselectionBinding

class AppselectionActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityAppselectionBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
    }

    private fun getInstalledApps(): List<Pair<String, Drawable?>> {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val packageManager = packageManager
        val resolveInfoList = packageManager.queryIntentActivities(intent, 0)
        return resolveInfoList.map {
            Pair(
                it.activityInfo.applicationInfo.loadLabel(packageManager).toString(),
                it.activityInfo.loadIcon(packageManager)
            )
        }
    }
}

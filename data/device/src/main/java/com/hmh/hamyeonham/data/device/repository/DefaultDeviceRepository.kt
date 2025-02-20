package com.hmh.hamyeonham.data.device.repository

import android.content.Context
import android.content.Intent
import com.hmh.hamyeonham.challenge.model.AppInfo
import com.hmh.hamyeonham.challenge.repository.DeviceRepository
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.context.isSystemPackage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultDeviceRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : DeviceRepository {
    override fun getInstalledApps(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfoList = context.packageManager.queryIntentActivities(intent, 0)
        return resolveInfoList.map {
            val packageName = it.activityInfo.packageName
            AppInfo(
                packageName = packageName,
                appName = context.getAppNameFromPackageName(packageName)
            )
        }.filter { !context.isSystemPackage(it.packageName) }.distinct()
    }
}

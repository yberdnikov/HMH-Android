package com.hmh.hamyeonham.domain

import android.health.connect.datatypes.units.Percentage
import androidx.annotation.DrawableRes

data class AppUsage(
    @DrawableRes
    val appIcon: Int,
    val packageName: String,
    val appName: String,
    val timeLeft: Int,
    val usedPercentage: Percentage,
)

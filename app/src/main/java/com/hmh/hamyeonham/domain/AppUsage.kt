package com.hmh.hamyeonham.domain

import androidx.annotation.DrawableRes

data class AppUsage(
    @DrawableRes
    val appIcon: Int,
    val packageName: String,
    val appName: String,
    val timeLeft: Int,
    val usedPercentage: Int,
)

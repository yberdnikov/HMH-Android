package com.hmh.hamyeonham.usagestats.model

data class UsageStatus(
    val packageName: String,
    val totalTimeInForeground: Long,
)

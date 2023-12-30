package com.hmh.hamyeonham.presentation.main

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.R
import com.hmh.hamyeonham.domain.AppUsage

class MainViewModel : ViewModel() {
    public val mockAppUsageList =
        mutableListOf<AppUsage>(
            AppUsage(
                appIcon = R.drawable.ic_launcher_foreground,
                packageName = "유튜브",
                appName = "유튜브",
                timeLeft = 2,
                usedPercentage = 40,
            ),
            AppUsage(
                appIcon = R.drawable.ic_launcher_foreground,
                packageName = "인스타그램",
                appName = "인스타그램",
                timeLeft = 2,
                usedPercentage = 40,
            ),
            AppUsage(
                appIcon = R.drawable.ic_launcher_foreground,
                packageName = "네이버",
                appName = "네이버",
                timeLeft = 2,
                usedPercentage = 40,
            ),
            AppUsage(
                appIcon = R.drawable.ic_launcher_foreground,
                packageName = "페이스북",
                appName = "페이스북",
                timeLeft = 1,
                usedPercentage = 90,
            ),
            AppUsage(
                appIcon = R.drawable.ic_launcher_foreground,
                packageName = "네이버 웹툰",
                appName = "네이버 웹툰",
                timeLeft = 3,
                usedPercentage = 10,
            ),
        )
}

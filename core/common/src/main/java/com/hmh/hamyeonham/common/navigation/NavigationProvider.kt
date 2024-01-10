package com.hmh.hamyeonham.common.navigation

import android.content.Intent

interface NavigationProvider {
    fun toOnBoarding(): Intent
    fun toLogin(): Intent
    fun toUserInfo(): Intent
    fun toStatics(): Intent
}

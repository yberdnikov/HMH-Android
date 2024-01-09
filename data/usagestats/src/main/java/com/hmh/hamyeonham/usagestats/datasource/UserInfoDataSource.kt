package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UserInfoModel
import javax.inject.Inject

class UserInfoDataSource @Inject constructor() {
    fun getUsageGoals(): UserInfoModel {
        return UserInfoModel("강화유리", 100)
    }
}

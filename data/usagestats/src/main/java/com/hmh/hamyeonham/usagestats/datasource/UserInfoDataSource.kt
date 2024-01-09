package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UserInfoModel
import javax.inject.Inject

class UserInfoDataSource @Inject constructor() {
    fun getUserInfoModel(): UserInfoModel {
        return UserInfoModel("여민서", 100)
    }
}

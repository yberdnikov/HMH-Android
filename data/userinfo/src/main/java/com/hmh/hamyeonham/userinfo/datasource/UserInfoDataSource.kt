package com.hmh.hamyeonham.userinfo.datasource

import com.hmh.hamyeonham.userinfo.model.UserInfoModel
import javax.inject.Inject

class UserInfoDataSource @Inject constructor() {

    fun getUserInfoModel(): UserInfoModel {
        return UserInfoModel("여민서", 100)
    }
}

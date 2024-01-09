package com.hmh.hamyeonham.userinfo.datasource

import javax.inject.Inject

class UserInfoDataSource @Inject constructor() {
    fun getUserInfoModel(): com.hmh.hamyeonham.userinfo.model.UserInfoModel {
        return com.hmh.hamyeonham.userinfo.model.UserInfoModel("여민서", 100)
    }
}

package com.hmh.hamyeonham.userinfo.datasource

import com.hmh.hamyeonham.userinfo.model.UserInfoModel
import javax.inject.Inject

class UserInfoDataSource
    @Inject
    constructor() {
        private val userInfoModel = UserInfoModel("여민서", 0)

        fun getUserInfoModel(): UserInfoModel {
            return userInfoModel
        }
    }

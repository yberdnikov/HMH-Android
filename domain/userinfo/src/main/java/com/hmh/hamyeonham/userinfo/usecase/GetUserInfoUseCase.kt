package com.hmh.hamyeonham.userinfo.usecase

import com.hmh.hamyeonham.userinfo.model.UserInfo
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase
    @Inject
    constructor(
        private val getUserInfo: UserInfoRepository,
    ) {
        operator fun invoke(): UserInfo {
            return getUserInfo.getUserInfo()
        }
    }

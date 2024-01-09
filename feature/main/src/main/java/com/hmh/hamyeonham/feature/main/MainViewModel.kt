package com.hmh.hamyeonham.feature.main

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.userinfo.model.UserInfo
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) : ViewModel() {
    val userInfo: MutableStateFlow<UserInfo> = MutableStateFlow<UserInfo>(UserInfo("", 0))

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        userInfo.value = userInfoRepository.getUserInfo()
    }
}

package com.hmh.hamyeonham.feature.main

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.userinfo.model.UserInfo
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) : ViewModel() {
    private lateinit var _userInfo: MutableStateFlow<UserInfo>
    val userInfo: MutableStateFlow<UserInfo> by lazy {
        _userInfo
    }

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        _userInfo.value = userInfoRepository.getUserInfo()
    }
}

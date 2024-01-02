package com.hmh.hamyeonham.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel() {

    private val _kakaoLogoutResult = MutableLiveData<Boolean>()
    val kakaoLogoutResult: LiveData<Boolean> get() = _kakaoLogoutResult

    private val _kakaoUserNickname = MutableLiveData<Boolean>()
    val kakaoUserNickname: LiveData<Boolean> get() = _kakaoUserNickname
}

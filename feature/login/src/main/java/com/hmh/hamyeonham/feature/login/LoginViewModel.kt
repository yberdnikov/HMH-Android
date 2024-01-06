package com.hmh.hamyeonham.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    private val _kakaoLoginResult = MutableLiveData<Boolean>()
    val kakaoLoginResult: LiveData<Boolean> get() = _kakaoLoginResult

}

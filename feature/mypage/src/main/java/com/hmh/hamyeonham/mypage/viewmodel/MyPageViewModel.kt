package com.hmh.hamyeonham.mypage.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

sealed interface UserEffect {
    data object logoutSuccess : UserEffect
    data object logoutFail : UserEffect

    data object withdrawalSuccess : UserEffect

    data object withdrawalFail : UserEffect
}

@HiltViewModel

class MyPageViewModel @Inject constructor() : ViewModel() {

    private val _userEffect = MutableSharedFlow<UserEffect>()
    val userEffect = _userEffect.asSharedFlow()


}


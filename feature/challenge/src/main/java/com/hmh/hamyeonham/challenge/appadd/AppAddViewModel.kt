package com.hmh.hamyeonham.challenge.appadd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface AppAddEffect {
    data class AppAdd(val selectedApp: List<String>, val goalTime: Long) : AppAddEffect
}

data class AppAddState(
    val selectedApp: List<String> = listOf(),
    val goalTime: Long = 0
)

class AppAddViewModel : ViewModel() {
    private val _appAddState = MutableStateFlow(AppAddState())
    val appAddState = _appAddState.asStateFlow()

    private val _appAddEffect = MutableSharedFlow<AppAddEffect>()
    val appAddEffect = _appAddEffect.asSharedFlow()

    fun updateState(transform: AppAddState.() -> AppAddState) {
        val currentState = appAddState.value
        val newState = currentState.transform()
        _appAddState.value = newState
    }
}

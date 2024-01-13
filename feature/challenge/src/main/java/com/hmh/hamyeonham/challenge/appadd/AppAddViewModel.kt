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
    private val _state = MutableStateFlow(AppAddState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AppAddEffect>()
    val effect = _effect.asSharedFlow()

    fun updateState(transform: AppAddState.() -> AppAddState) {
        val currentState = state.value
        val newState = currentState.transform()
        _state.value = newState
    }
}

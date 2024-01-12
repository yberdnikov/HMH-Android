package com.hmh.hamyeonham.challenge.appadd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppAddState(
    val selectedApp: List<String> = listOf(),
    val goalTime : Long = 0
)

class AppAddViewModel : ViewModel() {
    private val _appAddState = MutableStateFlow(AppAddState())
    val appAddState = _appAddState.asStateFlow()

    fun updateState(transform: AppAddState.() -> AppAddState) {
        _appAddState.value = _appAddState.value.transform()
    }

}

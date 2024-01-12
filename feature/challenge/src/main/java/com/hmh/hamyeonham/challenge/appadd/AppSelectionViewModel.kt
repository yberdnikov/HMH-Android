package com.hmh.hamyeonham.challenge.appadd

import androidx.lifecycle.ViewModel

class AppSelectionViewModel : ViewModel() {
    val selectedApp: MutableList<String> = mutableListOf()

    fun nonSelected(): Boolean = selectedApp.isEmpty()
}

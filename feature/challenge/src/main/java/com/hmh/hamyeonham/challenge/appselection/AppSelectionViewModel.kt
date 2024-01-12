package com.hmh.hamyeonham.challenge.appselection

import androidx.lifecycle.ViewModel

class AppSelectionViewModel : ViewModel() {
    val selectedApp: MutableList<String> = mutableListOf()

    fun nonSelected(): Boolean = selectedApp.isEmpty()
}

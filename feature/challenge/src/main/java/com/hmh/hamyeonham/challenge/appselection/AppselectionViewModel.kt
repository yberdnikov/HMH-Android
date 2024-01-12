package com.hmh.hamyeonham.challenge.appselection

import androidx.lifecycle.ViewModel

class AppselectionViewModel : ViewModel() {
    val selectedApp: MutableList<String> = mutableListOf()

    fun nonSelected(): Boolean = selectedApp.isEmpty()
}

package com.hmh.hamyeonham.challenge.appadd.appselection

import androidx.lifecycle.ViewModel

class AppSelectionViewModel : ViewModel() {
    val selectedApp: MutableList<String> = mutableListOf()

    fun nonSelected(): Boolean = selectedApp.isEmpty()
}

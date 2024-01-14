package com.hmh.hamyeonham.challenge.appadd.appselection

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.challenge.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppSelectionViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {
    fun getInstalledApps(): List<String> {
        return deviceRepository.getInstalledApps()
    }
}

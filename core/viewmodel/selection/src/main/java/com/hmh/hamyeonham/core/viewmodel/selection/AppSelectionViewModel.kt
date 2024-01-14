package com.hmh.hamyeonham.core.viewmodel.selection

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

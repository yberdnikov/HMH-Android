package com.hmh.hamyeonham.challenge.repository

interface DeviceRepository {
    fun getInstalledApps(): List<String>
}

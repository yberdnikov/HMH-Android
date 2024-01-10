package com.hmh.hamyeonham.core.network.datastore

interface HMHNetworkPreference {
    var accessToken: String
    var refreshToken: String
    var userName: String
    var userId: String
    var autoLoginConfigured: Boolean
    fun clear()
}

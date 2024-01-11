package com.hmh.hamyeonham.core.network.auth.datastore

interface HMHNetworkPreference {
    var accessToken: String
    var refreshToken: String
    var userName: String
    var userId: String
    var autoLoginConfigured: Boolean
    fun clear()
}

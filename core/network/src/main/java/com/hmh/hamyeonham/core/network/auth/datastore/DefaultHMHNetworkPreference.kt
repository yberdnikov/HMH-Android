package com.hmh.hamyeonham.core.network.auth.datastore

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class DefaultHMHNetworkPreference @Inject constructor(
    private val preferences: SharedPreferences,
) : HMHNetworkPreference {
    override var accessToken: String
        get() = preferences.getString("access_token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MDUxMjcyOTksImV4cCI6MTcwNTk5MTI5OSwidXNlcklkIjoxfQ.ify6miN838GJp_-uIXp0xQj3HTgWrFd3VDCCWx3PNQlE-FvZjqXgkx9eZ-sX_qjT").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("access_token", value)
            }
        }
    override var refreshToken: String
        get() = preferences.getString("refresh_token", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("refresh_token", value)
            }
        }
    override var userName: String
        get() = preferences.getString("user_name", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("user_name", value)
            }
        }
    override var userId: String
        get() = preferences.getString("user_id", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("user_id", value)
            }
        }
    override var autoLoginConfigured: Boolean
        get() = preferences.getBoolean("auto_login", false)
        set(value) {
            preferences.edit(commit = true) {
                putBoolean("auto_login", value)
            }
        }

    override fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }
}

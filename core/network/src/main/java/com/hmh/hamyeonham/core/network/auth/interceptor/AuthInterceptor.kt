package com.hmh.hamyeonham.core.network.auth.interceptor

import com.hmh.hamyeonham.core.network.datastore.HMHNetworkPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStore: HMHNetworkPreference
) : Interceptor {
    private val encodedToken: String
        get() = "Bearer ${dataStore.accessToken}"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (!shouldRequestAuthenticatedHeaders(originalRequest.url.encodedPath)) {
            return chain.proceed(originalRequest)
        }
        val headerRequest = originalRequest.newBuilder()
            .header("Authorization", encodedToken)
            .build()
        return chain.proceed(headerRequest)
    }

    private fun shouldRequestAuthenticatedHeaders(encodedPath: String) = when (encodedPath) {
        "/api/v1/auth" -> false
        else -> true
    }
}

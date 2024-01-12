package com.hmh.hamyeonham.core.network.auth.authenticator

import android.content.Context
import android.util.Log
import com.hmh.hamyeonham.common.navigation.NavigationProvider
import com.hmh.hamyeonham.core.network.auth.api.RefreshApi
import com.hmh.hamyeonham.core.network.auth.datastore.HMHNetworkPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HMHAuthenticator @Inject constructor(
    private val dataStore: HMHNetworkPreference,
    private val api: RefreshApi,
    @ApplicationContext private val context: Context,
    private val navigationProvider: NavigationProvider
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") == null) {
            return null
        }

        if (response.code == 401) {
            val refreshToken = dataStore.refreshToken
            val newTokens = runCatching {
                runBlocking {
                    api.refreshToken("Bearer $refreshToken")
                }
            }.onSuccess {
                val data = it.data
                dataStore.refreshToken = data.refreshToken.orEmpty()
                dataStore.accessToken = data.accessToken.orEmpty()
            }.onFailure {
                Log.e("Authenticator", it.toString())
                runBlocking {
                    //TODO 어떻게 처리할지 고민해보기
//                    UserApiClient.instance.logout { error ->
//                        Log.e("Authenticator", error.toString())
//                        dataStore.clear()
//                        ProcessPhoenix.triggerRebirth(context, navigationProvider.toOnboarding())
//                    }
                }
            }.getOrThrow()

            return response.request.newBuilder()
                .header("Authorization", "Bearer ${newTokens.data.accessToken}")
                .build()
        }
        return null
    }
}

package com.androidharness.musicplayer.data.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.androidharness.musicplayer.core.model.NetworkType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityMonitor @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val manager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun current(): NetworkType {
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
            ?: return NetworkType.OTHER
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->
                NetworkType.CELLULAR
            else -> NetworkType.OTHER
        }
    }

    fun isOnline(): Boolean {
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
            ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

package com.androidharness.musicplayer.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkType {
    WIFI,
    CELLULAR,
    OTHER;

    val isMetered: Boolean
        get() = this == CELLULAR
}

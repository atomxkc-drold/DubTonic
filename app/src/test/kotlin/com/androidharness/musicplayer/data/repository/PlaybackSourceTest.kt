package com.androidharness.musicplayer.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class PlaybackSourceTest {

    @Test
    fun prefersDownloadedFileWhenSettingIsOn() {
        assertEquals("file:///music/a.flac", PlaybackSource.localFor("file:///music/a.flac", true))
    }

    @Test
    fun ignoresDownloadedFileWhenSettingIsOff() {
        assertNull(PlaybackSource.localFor("file:///music/a.flac", false))
    }

    @Test
    fun missingOrBlankLocalUriNeverWins() {
        assertNull(PlaybackSource.localFor(null, true))
        assertNull(PlaybackSource.localFor("", true))
        assertNull(PlaybackSource.localFor("   ", true))
    }
}

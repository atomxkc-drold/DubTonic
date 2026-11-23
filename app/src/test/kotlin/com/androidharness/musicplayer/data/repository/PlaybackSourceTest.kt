package com.androidharness.musicplayer.data.repository

import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class PlaybackSourceTest {

    // java.io.tmpdir is not a usable directory in this environment, so fixtures live
    // beside the build output instead.
    private val dir: File = File("build/tmp/dubtonic-playback-source-test").apply { mkdirs() }

    private fun downloadedFile(): String =
        File(dir, "track-${System.nanoTime()}.flac").apply {
            writeBytes(byteArrayOf(1, 2, 3, 4))
            deleteOnExit()
        }.absolutePath

    @Test
    fun prefersDownloadedFileWhenSettingIsOn() {
        val path = downloadedFile()
        assertEquals("file://$path", PlaybackSource.localFor("file://$path", true))
        assertEquals(path, PlaybackSource.localFor(path, true))
    }

    @Test
    fun ignoresDownloadedFileWhenSettingIsOff() {
        assertNull(PlaybackSource.localFor("file://${downloadedFile()}", false))
    }

    @Test
    fun missingOrBlankLocalUriNeverWins() {
        assertNull(PlaybackSource.localFor(null, true))
        assertNull(PlaybackSource.localFor("", true))
        assertNull(PlaybackSource.localFor("   ", true))
    }

    @Test
    fun evictedDownloadFallsBackToStreaming() {
        val missing = File(dir, "gone-${System.nanoTime()}.flac")
        assertNull(PlaybackSource.localFor("file://${missing.absolutePath}", true))
    }

    @Test
    fun zeroByteDownloadFallsBackToStreaming() {
        val empty = File(dir, "empty-${System.nanoTime()}.flac").apply {
            createNewFile()
            deleteOnExit()
        }
        assertNull(PlaybackSource.localFor("file://${empty.absolutePath}", true))
    }

    @Test
    fun contentUrisAreTrusted() {
        assertEquals(
            "content://media/external/audio/media/42",
            PlaybackSource.localFor("content://media/external/audio/media/42", true),
        )
    }
}

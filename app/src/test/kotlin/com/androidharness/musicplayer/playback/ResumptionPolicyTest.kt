package com.androidharness.musicplayer.playback

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ResumptionPolicyTest {

    private val queue = StoredQueue(
        items = listOf(StoredQueueItem(id = "1", title = "Song")),
        index = 0,
        positionMs = 12_000,
    )

    @Test
    fun returnsStoredQueueWhenResumptionIsEnabled() {
        assertEquals(queue, ResumptionPolicy.resumable(queue, resumeQueueOnStart = true))
    }

    @Test
    fun dropsStoredQueueWhenResumptionIsDisabled() {
        assertNull(ResumptionPolicy.resumable(queue, resumeQueueOnStart = false))
    }

    @Test
    fun emptyOrMissingQueueNeverResumes() {
        assertNull(ResumptionPolicy.resumable(null, resumeQueueOnStart = true))
        assertNull(
            ResumptionPolicy.resumable(StoredQueue(), resumeQueueOnStart = true)
        )
    }
}

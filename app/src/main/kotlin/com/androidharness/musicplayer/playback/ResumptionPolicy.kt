package com.androidharness.musicplayer.playback

/**
 * Decides whether a stored queue may be handed back to the session at startup.
 *
 * Kept separate from the resolver so the rule is testable without Media3 or Android.
 */
object ResumptionPolicy {

    fun resumable(stored: StoredQueue?, resumeQueueOnStart: Boolean): StoredQueue? {
        if (!resumeQueueOnStart) return null
        return stored?.takeIf { it.items.isNotEmpty() }
    }
}

package com.androidharness.musicplayer.data.repository

import com.androidharness.musicplayer.data.local.stableRowId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class LibraryQueryTest {

    @Test
    fun blankQueryProducesEmptyExpression() {
        assertEquals("", "".toFtsQuery())
        assertEquals("", "   ".toFtsQuery())
    }

    @Test
    fun tokensBecomePrefixQueries() {
        assertEquals("\"dark\"* \"side\"*", "  dark   side ".toFtsQuery())
    }

    @Test
    fun quotesAreStrippedToAvoidFtsSyntaxErrors() {
        assertEquals("\"nightdrive\"*", "night\"drive".toFtsQuery())
    }

    @Test
    fun stableRowIdIsDeterministic() {
        val first = stableRowId("subsonic-track-42")
        val second = stableRowId("subsonic-track-42")
        assertEquals(first, second)
        assertNotEquals(first, stableRowId("subsonic-track-43"))
    }
}

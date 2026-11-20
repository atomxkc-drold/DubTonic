package com.androidharness.musicplayer.data.remote

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SubsonicAuthTest {

    @Test
    fun md5MatchesKnownVector() {
        assertEquals("900150983cd24fb0d6963f7d28e17f72", SubsonicAuth.md5Hex("abc"))
    }

    @Test
    fun tokenIsMd5OfPasswordAndSalt() {
        val salt = "c19b2d"
        val expected = SubsonicAuth.md5Hex("sesame$salt")
        val actual = SubsonicAuth.deriveToken("sesame", salt)
        assertEquals(expected, actual)
        assertEquals(32, actual.length)
        assertTrue(actual.all { it in "0123456789abcdef" })
    }

    @Test
    fun negotiateUsesOlderServerVersion() {
        assertEquals("1.14.0", SubsonicAuth.negotiate("1.16.1", "1.14.0"))
    }

    @Test
    fun negotiateCapsAtClientVersion() {
        assertEquals("1.16.1", SubsonicAuth.negotiate("1.16.1", "1.17.0"))
    }

    @Test
    fun negotiateFallsBackWhenServerVersionMissing() {
        assertEquals("1.13.0", SubsonicAuth.negotiate("1.16.1", null))
        assertEquals("1.13.0", SubsonicAuth.negotiate("1.16.1", ""))
    }

    @Test
    fun negotiateComparesNumericallyNotLexically() {
        assertEquals("1.9.0", SubsonicAuth.negotiate("1.10.0", "1.9.0"))
    }

    @Test
    fun saltIsRandomHex() {
        val first = SubsonicAuth.newSalt()
        val second = SubsonicAuth.newSalt()
        assertEquals(16, first.length)
        assertEquals(16, second.length)
        assertTrue(first != second)
    }
}

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
    fun saltIsRandomHex() {
        val first = SubsonicAuth.newSalt()
        val second = SubsonicAuth.newSalt()
        assertEquals(16, first.length)
        assertEquals(16, second.length)
        assertTrue(first != second)
    }
}

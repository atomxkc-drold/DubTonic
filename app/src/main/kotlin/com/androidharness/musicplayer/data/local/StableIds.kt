package com.androidharness.musicplayer.data.local

/**
 * Stable rowid derived from a string id. Subsonic ids are strings, while Room FTS content
 * sync needs an integer rowid. Hashing keeps the rowid stable across syncs, so @Upsert
 * updates rows instead of replacing them (which would cascade playlist entries).
 */
fun stableRowId(id: String): Long {
    var hash = -0x340d631b7bdddcdbL // FNV-1a 64-bit offset basis
    val bytes = id.toByteArray(Charsets.UTF_8)
    for (byte in bytes) {
        hash = hash xor (byte.toLong() and 0xffL)
        hash *= 0x100000001b3L
    }
    return hash
}

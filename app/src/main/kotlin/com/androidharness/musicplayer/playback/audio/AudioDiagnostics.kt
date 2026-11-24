package com.androidharness.musicplayer.playback.audio

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Writes the PCM format the audio sink is actually configured with to a file that can be
 * read without a logcat console or a privileged shell.
 *
 * Pitch-shifted ("chipmunk") playback is almost always a sample-rate or encoding mismatch,
 * and the decisive value is the format arriving at the processor chain versus the device's
 * native output rate. `onConfigure` runs on the playback thread, so the file write is
 * coalesced onto a single background thread and never blocks audio.
 */
object AudioDiagnostics {

    private const val TAG = "DubTonicAudio"
    private const val FILE_NAME = "audio-diagnostics.txt"
    private const val MAX_LINES = 160

    private val lines = ArrayDeque<String>()
    private val flushPending = AtomicBoolean(false)
    private val executor = Executors.newSingleThreadExecutor { runnable ->
        Thread(runnable, "audio-diagnostics").apply { isDaemon = true }
    }
    private val clock = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")

    @Volatile
    private var appContext: Context? = null

    fun install(context: Context) {
        appContext = context.applicationContext
    }

    fun record(message: String) {
        Log.i(TAG, message)
        synchronized(lines) {
            lines.addLast("${LocalTime.now().format(clock)} $message")
            while (lines.size > MAX_LINES) lines.removeFirst()
        }
        scheduleFlush()
    }

    private fun scheduleFlush() {
        if (!flushPending.compareAndSet(false, true)) return
        executor.execute {
            flushPending.set(false)
            runCatching { write() }
        }
    }

    private fun write() {
        val context = appContext ?: return
        val text = synchronized(lines) { lines.joinToString("\n") }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val dir = context.getExternalFilesDir(null) ?: return
            runCatching { File(dir, FILE_NAME).writeText(text) }
            return
        }
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, FILE_NAME)
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                "${Environment.DIRECTORY_DOWNLOADS}/HarnessMusic",
            )
        }
        val resolver = context.contentResolver
        val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.MediaColumns.DISPLAY_NAME}=?"
        runCatching { resolver.delete(collection, selection, arrayOf(FILE_NAME)) }
        val uri = resolver.insert(collection, values) ?: return
        resolver.openOutputStream(uri)?.use { stream -> stream.write(text.toByteArray()) }
    }
}

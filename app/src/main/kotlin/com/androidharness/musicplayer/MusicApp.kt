package com.androidharness.musicplayer

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import coil3.SingletonImageLoader
import com.androidharness.musicplayer.data.sync.SyncScheduler
import com.androidharness.musicplayer.ui.image.AppImageLoader
import dagger.hilt.android.HiltAndroidApp
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltAndroidApp
class MusicApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var syncScheduler: SyncScheduler

    @Inject
    lateinit var imageLoader: AppImageLoader

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    /**
     * Installed before Hilt injects the app, so a broken dependency graph or any
     * later failure still leaves a readable stack on disk.
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        installCrashLogger()
    }

    override fun onCreate() {
        super.onCreate()
        SingletonImageLoader.setSafe { imageLoader.loader }
        syncScheduler.schedule()
    }

    private fun installCrashLogger() {
        CrashReports.install(this)
    }
}

internal object CrashReports {

    private const val FILE_NAME = "crash-latest.txt"
    private const val START_FILE_NAME = "last-start.txt"

    fun install(context: Context) {
        val previous = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, error ->
            write(context, "crash", describe(error))
            previous?.uncaughtException(thread, error)
        }
        write(context, START_FILE_NAME, "started build=${BuildConfig.VERSION_NAME} at=${timestamp()}")
    }

    private fun describe(error: Throwable): String = buildString {
        appendLine("time=${timestamp()}")
        appendLine("version=${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
        appendLine("device=${Build.MODEL} sdk=${Build.VERSION.SDK_INT}")
        appendLine()
        appendLine(Log.getStackTraceString(error))
    }

    fun read(context: Context): String? = runCatching {
        val file = File(directory(context), FILE_NAME)
        if (file.exists()) file.readText() else null
    }.getOrNull()

    private fun directory(context: Context): File =
        context.getExternalFilesDir(null) ?: context.filesDir

    private fun write(context: Context, name: String, text: String) {
        runCatching {
            val dir = directory(context)
            if (!dir.exists()) dir.mkdirs()
            File(dir, name).writeText(text)
        }
        runCatching { publish(context, name, text) }
    }

    /**
     * The app external directory is not readable by other apps on API 30+, so the
     * dump is also published to Download/HarnessMusic through MediaStore, which
     * needs no storage permission.
     */
    private fun publish(context: Context, name: String, text: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                "${Environment.DIRECTORY_DOWNLOADS}/HarnessMusic",
            )
        }
        val resolver = context.contentResolver
        val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.MediaColumns.DISPLAY_NAME}=?"
        runCatching { resolver.delete(collection, selection, arrayOf(name)) }
        val uri = resolver.insert(collection, values) ?: return
        resolver.openOutputStream(uri)?.use { stream -> stream.write(text.toByteArray()) }
    }

    private fun timestamp(): String =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
}

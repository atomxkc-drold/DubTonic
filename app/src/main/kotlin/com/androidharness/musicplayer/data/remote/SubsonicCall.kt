package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.core.result.AppError
import com.androidharness.musicplayer.core.result.AppResult
import com.androidharness.musicplayer.data.remote.dto.SubsonicBody
import com.androidharness.musicplayer.data.remote.dto.SubsonicEnvelope
import java.io.IOException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException

class SubsonicException(
    val code: Int,
    override val message: String,
) : IOException(message)

fun <T : SubsonicBody> SubsonicEnvelope<T>.validated(): T {
    val body = response
    if (body.status.equals("ok", ignoreCase = true)) return body
    val error = body.error
    throw SubsonicException(
        code = error?.code ?: -1,
        message = error?.message ?: "Subsonic request failed",
    )
}

suspend fun <T> subsonicCall(block: suspend () -> T): AppResult<T> = try {
    AppResult.Success(block())
} catch (error: SubsonicNotConfiguredException) {
    AppResult.Failure(AppError.NotConfigured(error.message ?: "Server not configured"))
} catch (error: SubsonicException) {
    AppResult.Failure(AppError.Subsonic(error.code, error.message))
} catch (error: HttpException) {
    AppResult.Failure(AppError.Http(error.code(), error.message()))
} catch (error: SerializationException) {
    AppResult.Failure(AppError.Parse(error.message ?: "Malformed response", error))
} catch (error: IOException) {
    AppResult.Failure(AppError.Network(error.message ?: "Network error", error))
} catch (error: Exception) {
    AppResult.Failure(AppError.Unknown(error.message ?: "Unknown error", error))
}

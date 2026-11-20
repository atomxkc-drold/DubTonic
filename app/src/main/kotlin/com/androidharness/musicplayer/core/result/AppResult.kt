package com.androidharness.musicplayer.core.result

sealed interface AppResult<out T> {

    data class Success<T>(val value: T) : AppResult<T>

    data class Failure(val error: AppError) : AppResult<Nothing>

    fun getOrNull(): T? = (this as? Success)?.value

    fun errorOrNull(): AppError? = (this as? Failure)?.error
}

sealed interface AppError {
    val message: String

    data class Network(override val message: String, val cause: Throwable? = null) : AppError

    data class Http(val code: Int, override val message: String) : AppError

    data class Subsonic(val code: Int, override val message: String) : AppError

    data class NotConfigured(override val message: String) : AppError

    data class Parse(override val message: String, val cause: Throwable? = null) : AppError

    data class Unknown(override val message: String, val cause: Throwable? = null) : AppError
}

inline fun <T, R> AppResult<T>.map(transform: (T) -> R): AppResult<R> = when (this) {
    is AppResult.Success -> AppResult.Success(transform(value))
    is AppResult.Failure -> this
}

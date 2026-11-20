package com.androidharness.musicplayer.feature.home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

/**
 * The home hero changes with the clock: headline, copy and the warmth that tints the
 * aurora backdrop. Everything here is pure so the look can be tested without a device.
 */
enum class DayPhase { NIGHT, DAWN, MORNING, AFTERNOON, GOLDEN, EVENING }

data class DayMood(
    val phase: DayPhase,
    val kicker: String,
    val headline: String,
    val blurb: String,
    /** 0 = cold light, 1 = warm amber. */
    val warmth: Float,
)

fun dayMood(hour: Int): DayMood {
    val h = ((hour % 24) + 24) % 24
    return when (h) {
        in 0..4 -> DayMood(
            phase = DayPhase.NIGHT,
            kicker = "LATE NIGHT",
            headline = "Still spinning?",
            blurb = "The house is quiet. Let the queue run.",
            warmth = 0.12f,
        )
        in 5..7 -> DayMood(
            phase = DayPhase.DAWN,
            kicker = "FIRST LIGHT",
            headline = "Slow start.",
            blurb = "Something soft while the day boots up.",
            warmth = 0.42f,
        )
        in 8..11 -> DayMood(
            phase = DayPhase.MORNING,
            kicker = "MORNING",
            headline = "On your feet.",
            blurb = "Warm tones, easy rhythm, steady coffee.",
            warmth = 0.7f,
        )
        in 12..16 -> DayMood(
            phase = DayPhase.AFTERNOON,
            kicker = "AFTERNOON",
            headline = "Mid-day lift.",
            blurb = "A little energy for the long stretch.",
            warmth = 0.55f,
        )
        in 17..19 -> DayMood(
            phase = DayPhase.GOLDEN,
            kicker = "GOLDEN HOUR",
            headline = "Let it glow.",
            blurb = "Amber light and loud choruses.",
            warmth = 0.94f,
        )
        else -> DayMood(
            phase = DayPhase.EVENING,
            kicker = "EVENING",
            headline = "Wind down.",
            blurb = "Deep cuts, softer edges, lower lights.",
            warmth = 0.34f,
        )
    }
}

/** Greeting kicker with the real hour, e.g. "LATE NIGHT · 03:12". */
fun moodKicker(mood: DayMood, hour: Int, minute: Int): String {
    val h = ((hour % 24) + 24) % 24
    val m = ((minute % 60) + 60) % 60
    return "${mood.kicker} · ${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}"
}

/**
 * Colours for the drifting aurora. Album palette stays the base so the wall keeps
 * matching the art; the time of day only shifts its temperature.
 */
data class AuroraSpec(
    val blobs: List<Color>,
    val glow: Color,
    val shade: Color,
    /** 0 disables the infinite drift (reduced motion). */
    val driftMillis: Int,
)

private val WarmTint = Color(0xFFFFB27A)
private val CoolTint = Color(0xFF7C9BFF)

fun auroraFor(
    primary: Color,
    secondary: Color,
    mood: DayMood,
    reducedMotion: Boolean,
): AuroraSpec {
    val warmth = mood.warmth.coerceIn(0f, 1f)
    val tint = lerp(CoolTint, WarmTint, warmth)
    return AuroraSpec(
        blobs = listOf(
            lerp(primary, tint, 0.30f),
            lerp(secondary, tint, 0.22f),
            lerp(primary, tint, 0.55f),
        ),
        glow = lerp(tint, primary, 0.45f),
        shade = lerp(Color(0xFF07090B), primary, 0.16f),
        driftMillis = if (reducedMotion) 0 else (34_000L - (warmth * 12_000f)).toInt(),
    )
}

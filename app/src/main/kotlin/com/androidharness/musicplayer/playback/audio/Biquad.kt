package com.androidharness.musicplayer.playback.audio

import com.androidharness.musicplayer.data.settings.BandType
import com.androidharness.musicplayer.data.settings.EqualizerBand
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Direct Form II transposed biquad. One instance holds one channel's state.
 */
internal class Biquad(
    private val b0: Float,
    private val b1: Float,
    private val b2: Float,
    private val a1: Float,
    private val a2: Float,
) {
    private var z1 = 0f
    private var z2 = 0f

    fun process(input: Float): Float {
        val output = b0 * input + z1
        z1 = b1 * input - a1 * output + z2
        z2 = b2 * input - a2 * output
        return output
    }

    fun reset() {
        z1 = 0f
        z2 = 0f
    }

    companion object {
        fun unity(): Biquad = Biquad(1f, 0f, 0f, 0f, 0f)

        fun create(band: EqualizerBand, sampleRate: Int): Biquad {
            if (band.gainDb == 0f) return unity()

            val safeRate = sampleRate.coerceAtLeast(8_000)
            val frequency = band.frequencyHz.coerceIn(20f, safeRate * 0.45f)
            val q = band.q.coerceIn(0.1f, 10f)
            val amplitude = 10f.pow(band.gainDb / 40f)
            val omega = 2.0 * Math.PI * frequency / safeRate
            val sinOmega = sin(omega).toFloat()
            val cosOmega = cos(omega).toFloat()

            val coefficients = when (band.type) {
                BandType.PEAKING -> {
                    val alpha = sinOmega / (2f * q)
                    Coefficients(
                        b0 = 1f + alpha * amplitude,
                        b1 = -2f * cosOmega,
                        b2 = 1f - alpha * amplitude,
                        a0 = 1f + alpha / amplitude,
                        a1 = -2f * cosOmega,
                        a2 = 1f - alpha / amplitude,
                    )
                }
                BandType.LOW_SHELF -> {
                    val alpha = sinOmega / 2f * sqrt(
                        (amplitude + 1f / amplitude) * (1f / 1f - 1f) + 2f
                    )
                    val twoSqrtAAlpha = 2f * sqrt(amplitude) * alpha
                    Coefficients(
                        b0 = amplitude * ((amplitude + 1f) - (amplitude - 1f) * cosOmega +
                            twoSqrtAAlpha),
                        b1 = 2f * amplitude * ((amplitude - 1f) - (amplitude + 1f) * cosOmega),
                        b2 = amplitude * ((amplitude + 1f) - (amplitude - 1f) * cosOmega -
                            twoSqrtAAlpha),
                        a0 = (amplitude + 1f) + (amplitude - 1f) * cosOmega + twoSqrtAAlpha,
                        a1 = -2f * ((amplitude - 1f) + (amplitude + 1f) * cosOmega),
                        a2 = (amplitude + 1f) + (amplitude - 1f) * cosOmega - twoSqrtAAlpha,
                    )
                }
                BandType.HIGH_SHELF -> {
                    val alpha = sinOmega / 2f * sqrt(
                        (amplitude + 1f / amplitude) * (1f / 1f - 1f) + 2f
                    )
                    val twoSqrtAAlpha = 2f * sqrt(amplitude) * alpha
                    Coefficients(
                        b0 = amplitude * ((amplitude + 1f) + (amplitude - 1f) * cosOmega +
                            twoSqrtAAlpha),
                        b1 = -2f * amplitude * ((amplitude - 1f) + (amplitude + 1f) * cosOmega),
                        b2 = amplitude * ((amplitude + 1f) + (amplitude - 1f) * cosOmega -
                            twoSqrtAAlpha),
                        a0 = (amplitude + 1f) - (amplitude - 1f) * cosOmega + twoSqrtAAlpha,
                        a1 = 2f * ((amplitude - 1f) - (amplitude + 1f) * cosOmega),
                        a2 = (amplitude + 1f) - (amplitude - 1f) * cosOmega - twoSqrtAAlpha,
                    )
                }
            }

            return coefficients.toBiquad()
        }
    }

    private data class Coefficients(
        val b0: Float,
        val b1: Float,
        val b2: Float,
        val a0: Float,
        val a1: Float,
        val a2: Float,
    ) {
        fun toBiquad(): Biquad = Biquad(
            b0 = b0 / a0,
            b1 = b1 / a0,
            b2 = b2 / a0,
            a1 = a1 / a0,
            a2 = a2 / a0,
        )
    }
}

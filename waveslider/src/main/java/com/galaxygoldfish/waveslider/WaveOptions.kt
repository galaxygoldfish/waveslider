package com.galaxygoldfish.waveslider

/**
 * Provides customization of the sine wave section of
 * the slider
 *
 * @param amplitude - The height of the wave
 * @param frequency - How many waves appear in one section
 */
data class WaveOptions internal constructor(
    val amplitude: Float,
    val frequency: Float
)

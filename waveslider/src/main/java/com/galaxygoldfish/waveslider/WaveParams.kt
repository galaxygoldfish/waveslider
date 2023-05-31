package com.galaxygoldfish.waveslider

import androidx.compose.runtime.Stable

@Stable
data class WaveParams(
    val waveAnimationOptions: WaveAnimationOptions = WaveAnimationOptions(),
    val amplitude: Float = 4f,
    val frequency: Float = 0.07F
)
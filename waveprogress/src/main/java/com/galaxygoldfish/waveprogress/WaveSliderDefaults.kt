package com.galaxygoldfish.waveprogress

import android.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object WaveSliderDefaults {

    @Composable
    fun colors(
        thumbColor: Color = MaterialTheme.colorScheme.primary,
        activeTrackColor: Color = MaterialTheme.colorScheme.primary,
        inactiveTrackColor: Color = MaterialTheme.colorScheme.primaryContainer,
        disabledThumbColor: Color = MaterialTheme.colorScheme.primary.copy(0.5F),
        disabledActiveTrackColor: Color = MaterialTheme.colorScheme.primary.copy(0.5F),
        disabledInactiveTrackColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(0.5F)
    ) : WaveSliderColors = WaveSliderColors(
        thumbColor = thumbColor,
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
        disabledThumbColor = disabledThumbColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor
    )

}

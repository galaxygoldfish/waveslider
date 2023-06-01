/*   Copyright 2023 Sebastian Hriscu
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.galaxygoldfish.waveslider

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object WaveSliderDefaults {

    @Composable
    fun colors(
        thumbColor: Color = MaterialTheme.colorScheme.primary,
        activeTrackColor: Color = MaterialTheme.colorScheme.primary,
        activeTickColor: Color = MaterialTheme.colorScheme.surfaceVariant,
        inactiveTrackColor: Color = MaterialTheme.colorScheme.primaryContainer,
        inactiveTickColor: Color = MaterialTheme.colorScheme.primary.copy(0.5F),
        disabledThumbColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        disabledActiveTickColor: Color = MaterialTheme.colorScheme.primary.copy(0.5F),
        disabledActiveTrackColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        disabledInactiveTickColor: Color = MaterialTheme.colorScheme.primary.copy(0.3F),
        disabledInactiveTrackColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(0.5F)
    ): WaveSliderColors = WaveSliderColors(
        thumbColor = thumbColor,
        activeTrackColor = activeTrackColor,
        activeTickColor = activeTickColor,
        inactiveTrackColor = inactiveTrackColor,
        inactiveTickColor = inactiveTickColor,
        disabledThumbColor = disabledThumbColor,
        disabledActiveTickColor = disabledActiveTickColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTickColor = disabledInactiveTickColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor
    )

    @Composable
    fun animationOptions(
        reverseDirection: Boolean = false,
        flatlineOnDrag: Boolean = true,
        animateWave: Boolean = true,
        reverseFlatline: Boolean = false
    ): WaveAnimationOptions = WaveAnimationOptions(
        reverseDirection = reverseDirection,
        flatlineOnDrag = flatlineOnDrag,
        animateWave = animateWave,
        reverseFlatline = reverseFlatline
    )

    @Composable
    fun waveOptions(
        amplitude: Float = 15F,
        frequency: Float = 0.07F
    ): WaveOptions = WaveOptions(
        amplitude = amplitude,
        frequency = frequency
    )

}

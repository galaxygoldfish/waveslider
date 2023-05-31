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

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.sin

/**
 * Animated wavy slider component similar to the one seen in the
 * Android 13 media player notification.
 *
 * @param value Current value representing the progress of the slider
 * @param onValueChange Callback in which value should be updated
 * @param colors [WaveSliderColors] representing the colors of the
 * slider in various states. See [WaveSliderDefaults.colors]
 * @param enabled Whether the slider will respond to user input
 * @param thumb Can be a custom composable used for the thumb of the
 * slider. By default, a [PillThumb] is used. If you are creating a custom
 * thumb, use [LocalThumbColor] to match the colors of the rest of the slider
 * @param waveParams A [WaveParams] used to customize
 * the wave animation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaveSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (Float) -> Unit = {},
    colors: WaveSliderColors = WaveSliderDefaults.colors(),
    enabled: Boolean = true,
    thumb: @Composable () -> Unit = { PillThumb() },
    waveParams: WaveParams = WaveParams(),
    modifier: Modifier = Modifier
) {
    val amplitude = waveParams.amplitude
    val frequency = waveParams.frequency
    val animationOptions = waveParams.waveAnimationOptions

    var isDragging by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is DragInteraction.Start -> {
                    isDragging = true
                }

                is DragInteraction.Stop -> {
                    isDragging = false
                }
            }
        }
    }
    val infiniteTransition = rememberInfiniteTransition(label = "Wave infinite transition")
    val phaseShiftFloat = infiniteTransition.animateFloat(
        label = "Wave phase shift",
        initialValue = 0F,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
            },
            repeatMode = RepeatMode.Restart
        )
    ).value
    Slider(
        steps = waveParams.steps,
        value = value,
        onValueChangeFinished = {
            onValueChangeFinished(value)
        },
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        enabled = enabled,
        modifier = modifier,
        thumb = {
            CompositionLocalProvider(
                LocalThumbColor provides if (enabled) {
                    colors.thumbColor
                } else {
                    colors.disabledThumbColor
                }
            ) {
                thumb()
            }
        },
        track = {
            val animatedAmplitude = animateFloatAsState(
                targetValue = if (animationOptions.flatlineOnDrag) {
                    if (animationOptions.reverseFlatline) {
                        if (isDragging) amplitude else 0F
                    } else {
                        if (isDragging) 0F else amplitude
                    }
                } else {
                    amplitude
                },
                label = "Wave amplitude"
            ).value
            Canvas(modifier = Modifier.fillMaxWidth()) {
                val centerY = size.height / 2f
                val startX = 0F
                val endX = size.width * value
                val path = Path()
                for (x in startX.toInt()..endX.toInt()) {
                    var modifiedX = x.toFloat()
                    if (animationOptions.animateWave && enabled) {
                        if (animationOptions.reverseDirection) {
                            modifiedX += phaseShiftFloat
                        } else {
                            modifiedX -= phaseShiftFloat
                        }
                    }
                    val y = (animatedAmplitude * sin(frequency * modifiedX))
                    path.moveTo(x.toFloat(), centerY - y)
                    path.lineTo(x.toFloat(), centerY - y)
                }
                drawPath(
                    path = path,
                    color = if (enabled) {
                        colors.activeTrackColor
                    } else {
                        colors.disabledActiveTrackColor
                    },
                    style = Stroke(width = 8f, cap = StrokeCap.Round)
                )
                drawLine(
                    color = if (enabled) {
                        colors.inactiveTrackColor
                    } else {
                        colors.disabledInactiveTrackColor
                    },
                    strokeWidth = 8F,
                    cap = StrokeCap.Round,
                    start = Offset(endX + 1, centerY),
                    end = Offset(size.width, centerY)
                )
            }
        }
    )
}


/**
 * Animated wavy slider component similar to the one seen in the
 * Android 13 media player notification.
 *
 * @param value Current value representing the progress of the slider
 * @param onValueChange Callback in which value should be updated
 * @param amplitude Amplitude of the waves (sin waves)
 * @param frequency Frequency of the waves (sin waves)
 * @param colors [WaveSliderColors] representing the colors of the
 * slider in various states. See [WaveSliderDefaults.colors]
 * @param enabled Whether the slider will respond to user input
 * @param thumb Can be a custom composable used for the thumb of the
 * slider. By default, a [PillThumb] is used. If you are creating a custom
 * thumb, use [LocalThumbColor] to match the colors of the rest of the slider
 * @param animationOptions A [WaveAnimationOptions] used to customize
 * the wave animation
 */
@Deprecated("Consider using WaveParams instead", replaceWith = ReplaceWith("WaveSlider"))
@Composable
fun WaveSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (Float) -> Unit = {},
    amplitude: Float = 15F,
    frequency: Float = 0.07F,
    animationOptions: WaveAnimationOptions = WaveAnimationOptions(),
    colors: WaveSliderColors = WaveSliderDefaults.colors(),
    enabled: Boolean = true,
    thumb: @Composable () -> Unit = { PillThumb() },
    modifier: Modifier = Modifier
) {
    WaveSlider(
        value = value,
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        waveParams = WaveParams(
            waveAnimationOptions = animationOptions,
            amplitude = amplitude,
            frequency = frequency,
        ),
        colors = colors,
        enabled = enabled,
        thumb = thumb,
        modifier = modifier
    )
}
package com.waveprogress

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.waveprogress.ui.theme.WaveProgressTheme
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.PI
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveProgressTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val colors = MaterialTheme.colorScheme
                    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                        var sliderValue by remember { mutableStateOf(0.4F) }
                        Slider(
                            value = sliderValue,
                            onValueChange = {
                                sliderValue = it
                            },
                            track = { sliderPositions ->
                                val amplitude = 15f // Amplitude of the wave
                                val frequency = 0.07f // Frequency of the wave
                                Canvas(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(10.dp)
                                ) {
                                    val centerY = size.height / 2f
                                    val startX = 0F
                                    val endX = size.width * sliderPositions.positionFraction
                                    val path = Path()
                                    path.moveTo(startX, centerY)
                                    for (x in startX.toInt()..endX.toInt()) {
                                        val phaseShiftedX = x + 1
                                        val y = (amplitude * sin(frequency * phaseShiftedX)).toFloat()
                                        path.lineTo(x.toFloat(), centerY - y)
                                    }
                                    drawPath(
                                        path = path,
                                        color = colors.primary,
                                        style = Stroke(width = 8f, cap = StrokeCap.Round)
                                    )
                                    drawLine(
                                        color = colors.primaryContainer,
                                        strokeWidth = 8F,
                                        cap = StrokeCap.Round,
                                        start = startX
                                    )
                                }
                            },
                            thumb = {
                                Box(Modifier.width(15.dp)) {
                                    Column(
                                        modifier = Modifier
                                            .size(6.dp, 30.dp)
                                            .clip(MaterialTheme.shapes.small)
                                            .background(MaterialTheme.colorScheme.primary)
                                            .align(Alignment.CenterEnd)
                                    ) {}
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


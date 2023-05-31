package com.waveslider.sample

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.galaxygoldfish.waveslider.CircleThumb
import com.galaxygoldfish.waveslider.DiamondThumb
import com.galaxygoldfish.waveslider.LocalThumbColor
import com.galaxygoldfish.waveslider.PillThumb
import com.galaxygoldfish.waveslider.SquareThumb
import com.galaxygoldfish.waveslider.WaveAnimationOptions
import com.galaxygoldfish.waveslider.WaveParams
import com.galaxygoldfish.waveslider.WaveSlider
import com.waveslider.sample.theme.SetSystemBarColors
import com.waveslider.sample.theme.WaveProgressTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveProgressTheme {
                SetSystemBarColors(
                    colorStatus = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    colorNav = MaterialTheme.colorScheme.surface
                )
                var reverseDirection by remember { mutableStateOf(false) }
                var flatlineOnDrag by remember { mutableStateOf(true) }
                var animateWave by remember { mutableStateOf(true) }
                var reverseFlatline by remember { mutableStateOf(false) }
                val thumbChoice = remember { mutableStateOf(0) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Row {
                                        Text(stringResource(id = R.string.app_name))
                                    }
                                },
                                actions = {
                                    Card(
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                        ),
                                        modifier = Modifier.padding(end = 15.dp)
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.demo_chip),
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(
                                                vertical = 5.dp,
                                                horizontal = 10.dp
                                            )
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                        2.dp
                                    )
                                )
                            )
                        }
                    ) { paddingValues ->
                        Column(
                            Modifier
                                .padding(paddingValues)
                                .verticalScroll(rememberScrollState())
                        ) {
                            var sliderValue by remember { mutableStateOf(0.4F) }
                            WaveSlider(
                                value = sliderValue,
                                onValueChange = { sliderValue = it },
                                waveParams = WaveParams(
                                    waveAnimationOptions = WaveAnimationOptions(
                                        reverseDirection = reverseDirection,
                                        flatlineOnDrag = flatlineOnDrag,
                                        animateWave = animateWave,
                                        reverseFlatline = reverseFlatline
                                    )
                                ),
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp),
                                thumb = {
                                    AnimatedContent(
                                        targetState = thumbChoice.value,
                                        label = "Slider thumb"
                                    ) { state ->
                                        when (state) {
                                            0 -> PillThumb()
                                            1 -> SquareThumb()
                                            2 -> CircleThumb()
                                            3 -> DiamondThumb()
                                        }
                                    }
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                CardPreference(
                                    index = 0,
                                    content = {
                                        Column(Modifier.padding(end = 10.dp)) {
                                            PillThumb()
                                        }
                                    },
                                    thumbChoice
                                )
                                CardPreference(
                                    index = 1,
                                    content = { SquareThumb() },
                                    thumbChoice
                                )
                                CardPreference(
                                    index = 2,
                                    content = { CircleThumb() },
                                    thumbChoice
                                )
                                CardPreference(
                                    index = 3,
                                    content = { DiamondThumb() },
                                    thumbChoice
                                )
                            }
                            Column(modifier = Modifier.padding(top = 20.dp)) {
                                SwitchPreference(
                                    title = stringResource(R.string.preference_animatewave_title),
                                    subtitle = stringResource(R.string.preference_animatewave_subtitle),
                                    value = animateWave,
                                    onValueChange = { animateWave = it }
                                )
                                SwitchPreference(
                                    title = stringResource(R.string.preference_reversedirection_title),
                                    subtitle = stringResource(R.string.preference_reversedirection_subtitle),
                                    value = reverseDirection,
                                    onValueChange = { reverseDirection = it }
                                )
                                SwitchPreference(
                                    title = stringResource(R.string.preference_flatlineondrag_title),
                                    subtitle = stringResource(R.string.preference_flatlineondrag_subtitle),
                                    value = flatlineOnDrag,
                                    onValueChange = { flatlineOnDrag = it }
                                )
                                SwitchPreference(
                                    title = stringResource(R.string.preference_reverseflatline_title),
                                    subtitle = stringResource(R.string.preference_reverseflatline_subtitle),
                                    value = reverseFlatline,
                                    onValueChange = { reverseFlatline = it }
                                )
                                FilledTonalButton(
                                    onClick = {
                                        Intent(ACTION_VIEW).apply {
                                            data = Uri.parse("https://github.com/galaxygoldfish/waveslider")
                                            this@MainActivity.startActivity(this)
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 30.dp, end = 20.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_github),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text = stringResource(id = R.string.button_view_external),
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SwitchPreference(
        title: String,
        subtitle: String,
        value: Boolean,
        onValueChange: (Boolean) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onValueChange(!value)
                }
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.fillMaxWidth(0.75F)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Switch(
                checked = value,
                onCheckedChange = onValueChange,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPreference(
    index: Int,
    content: @Composable () -> Unit,
    thumbChoice: MutableState<Int>
) {
    Card(
        onClick = { thumbChoice.value = index },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = if (thumbChoice.value == index) 3.dp else 1.dp,
            color = if (thumbChoice.value == index) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        )
    ) {
        Column(
            modifier = Modifier.size(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(LocalThumbColor provides MaterialTheme.colorScheme.primary) {
                content()
            }
        }
    }
}


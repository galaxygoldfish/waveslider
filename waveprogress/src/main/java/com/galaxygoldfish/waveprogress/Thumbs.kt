package com.galaxygoldfish.waveprogress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PillThumb() {
    Box(Modifier.width(15.dp)) {
        Column(
            modifier = Modifier
                .size(6.dp, 30.dp)
                .clip(MaterialTheme.shapes.small)
                .background(LocalThumbColor.current)
                .align(Alignment.CenterEnd)
        ) {}
    }
}
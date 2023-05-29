package com.galaxygoldfish.waveslider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val LocalThumbColor = compositionLocalOf { Color.Transparent }

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

@Composable
fun SquareThumb() {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(LocalThumbColor.current)
    )
}

@Composable
fun CircleThumb() {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(LocalThumbColor.current)
    )
}

@Composable
fun DiamondThumb() {
    Box(
        modifier = Modifier
            .size(18.dp)
            .rotate(45F)
            .clip(RoundedCornerShape(2.dp))
            .background(LocalThumbColor.current)
    )
}
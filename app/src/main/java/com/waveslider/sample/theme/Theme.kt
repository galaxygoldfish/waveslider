package com.waveslider.sample.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SetSystemBarColors(
    colorStatus: Color,
    colorNav: Color,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    LocalView.current.apply {
        if (!isInEditMode) {
            SideEffect {
                val window = (context as Activity).window
                window.statusBarColor = colorStatus.toArgb()
                window.navigationBarColor = colorNav.toArgb()
                WindowCompat.getInsetsController(window, this).apply {
                    isAppearanceLightStatusBars = !darkTheme
                    isAppearanceLightNavigationBars = !darkTheme
                }
            }
        }
    }
}

@Composable
fun WaveProgressTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }
    SetSystemBarColors(colorStatus = colorScheme.surface, colorNav = colorScheme.surface)
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
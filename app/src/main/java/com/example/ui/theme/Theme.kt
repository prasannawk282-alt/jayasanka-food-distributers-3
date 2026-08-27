package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = NaturalOlive,
    onPrimary = Color.White,
    primaryContainer = NaturalOliveSoft,
    onPrimaryContainer = NaturalOliveDark,
    secondary = GoldenHoney,
    onSecondary = Color.White,
    secondaryContainer = SurfaceWarm,
    onSecondaryContainer = EarthEspresso,
    tertiary = NaturalOliveLight,
    onTertiary = Color.White,
    tertiaryContainer = NaturalOliveSoft,
    onTertiaryContainer = NaturalOliveDark,
    background = CreamBackground,
    onBackground = EarthEspresso,
    surface = CardSurface,
    onSurface = EarthEspresso,
    surfaceVariant = SurfaceVariantSoft,
    onSurfaceVariant = EarthKhaki,
    outline = BorderNeutral,
    outlineVariant = Color(0xFFF0EEE6),
    error = DangerRed,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = NaturalOliveLight,
    onPrimary = Color.White,
    primaryContainer = NaturalOliveDark,
    onPrimaryContainer = NaturalOliveSoft,
    secondary = GoldenHoney,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF3B332A),
    onSecondaryContainer = Color(0xFFEDE3D2),
    tertiary = NaturalOliveSoft,
    onTertiary = NaturalOliveDark,
    background = Color(0xFF1C1A17),
    onBackground = Color(0xFFF7F4EE),
    surface = Color(0xFF26231F),
    onSurface = Color(0xFFF7F4EE),
    surfaceVariant = Color(0xFF36322C),
    onSurfaceVariant = Color(0xFFD6CEBF),
    outline = Color(0xFF524C43)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


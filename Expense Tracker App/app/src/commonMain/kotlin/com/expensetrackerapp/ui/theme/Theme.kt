package com.expensetrackerapp.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color // Ensure this import is present

// Define some basic colors, can be expanded later
private val PrimaryLight = Color(0xFF6200EE)
private val SecondaryLight = Color(0xFF03DAC5)
private val BackgroundLight = Color(0xFFFFFFFF)
private val SurfaceLight = Color(0xFFFFFFFF)
private val OnPrimaryLight = Color.White
private val OnSecondaryLight = Color.Black
private val OnBackgroundLight = Color.Black
private val OnSurfaceLight = Color.Black

private val PrimaryDark = Color(0xFFBB86FC)
private val SecondaryDark = Color(0xFF03DAC5) // Often the same or similar
private val BackgroundDark = Color(0xFF121212)
private val SurfaceDark = Color(0xFF121212) // Or a slightly different dark shade
private val OnPrimaryDark = Color.Black
private val OnSecondaryDark = Color.Black
private val OnBackgroundDark = Color.White
private val OnSurfaceDark = Color.White


val AppLightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = OnPrimaryLight,
    onSecondary = OnSecondaryLight,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight
    // You can define other colors like error, tertiary, etc.
)

val AppDarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark
    // You can define other colors
)

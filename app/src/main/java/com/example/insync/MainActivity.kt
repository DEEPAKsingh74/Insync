package com.example.insync

import OnboardingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.insync.presentation.navigation.AppNavigation
import com.example.insync.presentation.screens.Home.HomeScreen
import com.example.insync.presentation.ui.theme.InsyncTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InsyncTheme {
                AppNavigation(isLoggedIn = true)
            }
        }
    }
}

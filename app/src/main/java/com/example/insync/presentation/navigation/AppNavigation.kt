package com.example.insync.presentation.navigation

import OnboardingScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.insync.presentation.screens.Home.HomeScreen

@Composable
fun AppNavigation(
    isLoggedIn: Boolean
) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) NavDestination.Home.route else NavDestination.Onboarding.route
    ) {
        composable(NavDestination.Onboarding.route) {
            OnboardingScreen(navHostController)
        }

        composable(NavDestination.Home.route) {
            HomeScreen(navHostController)
        }
    }
}
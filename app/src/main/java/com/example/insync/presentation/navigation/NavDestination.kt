package com.example.insync.presentation.navigation

sealed class NavDestination(val route: String) {
    data object Onboarding : NavDestination("onboarding")
    data object Home : NavDestination("home")
}

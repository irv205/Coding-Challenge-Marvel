package com.irv205.challengedecember.presentation.main.navigation

sealed class NavigationViews(val route: String) {
    object HomeView : NavigationViews("home")
    object DetailView : NavigationViews("detail")
}
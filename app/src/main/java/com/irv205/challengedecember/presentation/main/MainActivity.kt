package com.irv205.challengedecember.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irv205.challengedecember.presentation.main.navigation.NavigationViews
import com.irv205.challengedecember.presentation.MainViewModel
import com.irv205.challengedecember.presentation.detail.DetailView
import com.irv205.challengedecember.presentation.home.HomeBody
import com.irv205.challengedecember.presentation.home.HomeView
import com.irv205.challengedecember.ui.theme.ChallengeDecemberTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeDecemberTheme {
                val navController = rememberNavController()
                val vm: MainViewModel = viewModel()
                NavHost(
                    navController = navController, startDestination = NavigationViews.HomeView.route){
                    composable(NavigationViews.HomeView.route) {
                        HomeView(vm) { navController.navigate(NavigationViews.DetailView.route) }
                    }
                    composable(NavigationViews.DetailView.route){
                        DetailView(vm, onBackHome = {
                            navController.navigate(NavigationViews.HomeView.route){
                                popUpTo(NavigationViews.HomeView.route)
                                launchSingleTop = true
                            }
                        })
                    }
                }
            }
        }
    }
}
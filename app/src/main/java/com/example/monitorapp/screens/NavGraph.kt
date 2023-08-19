package com.example.monitorapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun DoNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen) {
        composable(Screens.SingUpScreen) {
            SignUp(navController = navController)
        }
        composable(Screens.LoginScreen) {
            Login(navController = navController)
        }
        composable(Screens.HomeScreen){
            HomeScreen()
        }
    }
}
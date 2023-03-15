package com.app.newg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.newg.ui.screens.*

@Composable
fun AppNavigation(LoginViewModel: Any?){
    val navigationController = rememberNavController()
    NavHost(navController = navigationController,
        startDestination = AppScreens.LoginScreen.route)
    {
        composable(AppScreens.SaveG.route){ SaveG(navigationController) }
        composable(AppScreens.DeleteG.route){ DeleteG(navigationController) }
        composable(AppScreens.UpdateG.route){ UpdateG(navigationController) }
        composable(AppScreens.SearchG.route){ SearchG(navigationController) }
        composable(AppScreens.MenuInicio.route){ MenuInicio(navigationController)}
        composable(AppScreens.LoginScreen.route){ LoginScreen(navigationController) }
    }
}
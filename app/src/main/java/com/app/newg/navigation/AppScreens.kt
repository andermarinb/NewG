package com.app.newg.navigation

sealed class AppScreens (val route: String){
    object LoginScreen: AppScreens("LoginScreen")
    object SaveG: AppScreens("SaveG")
    object DeleteG: AppScreens("DeleteG")
    object UpdateG: AppScreens("UpdateG")
    object SearchG: AppScreens("SearchG")
    object MenuInicio: AppScreens("MenuInicio")
}

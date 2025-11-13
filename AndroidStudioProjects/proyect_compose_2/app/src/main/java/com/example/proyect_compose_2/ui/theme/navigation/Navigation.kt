package com.example.proyect_compose_2.ui.theme.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyect_compose_2.ui.theme.navigation.Screen
//import com.example.my_application.ui.theme.SettingsScreen
import com.example.proyect_compose_2.ui.theme.screens.DetailsScreen
import com.example.proyect_compose_2.ui.theme.screens.HomeScreen

@SuppressLint()
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Pantalla Home
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigationToDetails = { id ->
                    navController.navigate(Screen.Details.createRoute(id))
                },
                onNavigationToSettings = {
                    navController.navigate(Screen.Settings.route)//Deplecado
                }
            )
        }

        // Pantalla Details
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(name = Screen.Details.arg) {
                type = NavType.IntType
            })
        ) { stackEntry ->
            val id = stackEntry.arguments?.getInt(Screen.Details.arg) ?: -1
            DetailsScreen(
                id = id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

//        //  Pantalla Settings (deplecada)
//        composable(route = Screen.Settings.route) {
//            SettingsScreen(
//                onBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
    }
}
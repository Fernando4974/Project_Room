package com.example.proyect_compose_2.ui.theme.navigation


// Definición pantallas
sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object Details : Screen(route = "details/{id}") {
        fun createRoute(id: Int) = "details/$id"
        const val arg = "id"
    }
    data object Settings : Screen(route = "settings")
}
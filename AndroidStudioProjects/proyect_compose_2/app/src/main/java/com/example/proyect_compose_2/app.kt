package com.example.proyect_compose_2

import androidx.compose.runtime.Composable
import com.example.proyect_compose_2.ui.theme.navigation.AppNavGraph
import androidx.navigation.compose.rememberNavController

@Composable
fun App() {
    val nav = rememberNavController()
    AppNavGraph(navController = nav)
}
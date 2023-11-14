/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */

package projet.mobile.kotlin.fitsv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import projet.mobile.kotlin.fitsv.ui.screens.HomeScreen
import projet.mobile.kotlin.fitsv.ui.screens.ProgramsScreen
import projet.mobile.kotlin.fitsv.ui.screens.SettingsScreen

/**
 * Function used to setup destination of the routes of the navBar
 */
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Programs.route) {
            ProgramsScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}
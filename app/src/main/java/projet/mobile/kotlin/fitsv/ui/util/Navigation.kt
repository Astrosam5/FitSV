/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.util

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.wear.compose.material.ContentAlpha
import projet.mobile.kotlin.fitsv.ui.screens.login.LoginScreen
import projet.mobile.kotlin.fitsv.ui.screens.login.SingUpScreen
import projet.mobile.kotlin.fitsv.ui.routes.BottomBarRoutes
import projet.mobile.kotlin.fitsv.ui.routes.SettingsRoutes
import projet.mobile.kotlin.fitsv.ui.screens.HomeScreen
import projet.mobile.kotlin.fitsv.ui.screens.ProgramsScreen
import projet.mobile.kotlin.fitsv.ui.screens.SettingsScreen


/**
 * Function used to setup the navigation
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(windowSize: WindowSize) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            when (windowSize.width) {
                WindowType.Compact -> {
                    BottomBar(navController = navController)
                }
                WindowType.Medium -> {
                    NavigationRailBar(navController = navController)
                }
                WindowType.Expanded -> {
                    NavigationRailBar(navController = navController)
                }
            }
        }
    ) {

        // Nav Bar route
        NavHost(
            navController = navController,
            startDestination = BottomBarRoutes.Home.route
        ) {
            composable(route = BottomBarRoutes.Home.route) {
                HomeScreen(windowSize = windowSize)
            }
            composable(route = BottomBarRoutes.Programs.route) {
                ProgramsScreen(windowSize = windowSize)
            }
            composable(route = BottomBarRoutes.Settings.route) {
                SettingsScreen(
                    windowSize = windowSize,
                    onNavigateToLogin = { navController.navigate(SettingsRoutes.Login.route)}
                )
            }
            composable(route=SettingsRoutes.Login.route) {
                LoginScreen(
                    onNavigateToSingUp = {navController.navigate(SettingsRoutes.SingUp.route)},
                    onNavigateToHomeScreen = { navController.navigate(BottomBarRoutes.Home.route)}
                )
            }
            composable(route = SettingsRoutes.SingUp.route) {
                SingUpScreen(onNavigateToHomeScreen = {navController.navigate(BottomBarRoutes.Home.route)})
            }
        }
    }
}

/**
 * Function used to setup elements on the BottomBar on the screen
 * @param navController: NavHostController used for navigation
 */
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarRoutes.Home,
        BottomBarRoutes.Programs,
        BottomBarRoutes.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

/**
 * Function used to setup elements on the Navigation rail on the screen
 * @param navController: NavHostController used for navigation
 */
@Composable
fun NavigationRailBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarRoutes.Home,
        BottomBarRoutes.Programs,
        BottomBarRoutes.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationRail {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


/**
 * Function used to add items to the BottomBar
 * @param screen: to add item
 * @param currentDestination: current location
 * @param navController: NavHostController used for navigation
 */
@Composable
fun RowScope.AddItem(
    screen: BottomBarRoutes,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

/**
 * Function used to add items to the BottomBar
 * @param screen: to add item
 * @param currentDestination: current location
 * @param navController: NavHostController used for navigation
 */
@Composable
fun ColumnScope.AddItem(
    screen: BottomBarRoutes,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationRailItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
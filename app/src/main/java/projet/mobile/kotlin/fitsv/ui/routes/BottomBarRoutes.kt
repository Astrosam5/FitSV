/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Sealed class BottomBarScreen
 * Used to define routes of the navBar
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
sealed class BottomBarRoutes(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarRoutes(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Programs : BottomBarRoutes(
        route = "programs",
        title = "Programs",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarRoutes(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}

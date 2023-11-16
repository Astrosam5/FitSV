/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.routes

/**
 * Sealed class SettingsRoutes
 * Used to define routes of the setting screen
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
sealed class SettingsRoutes (
    val route: String,
    val title: String,
) {

    data object Login : SettingsRoutes(
        route = "login",
        title = "Login"
    )

    data object SingUp : SettingsRoutes(
        route = "sing_up",
        title = "SingUp"
    )
}
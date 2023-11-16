/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import projet.mobile.kotlin.fitsv.ui.util.WindowSize
import projet.mobile.kotlin.fitsv.ui.util.WindowType
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize

/**
 * Function used to define UI of the SettingsScreen
 */
@Composable
fun SettingsScreen(
    windowSize: WindowSize,
    onNavigateToLogin: () -> Unit
) {
    when (windowSize.width) {
        WindowType.Compact -> {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Button(onClick = onNavigateToLogin) {
                            Text(text = "Login")
                        }
                        Text(
                            text = "SETTINGS",
                            fontSize = MaterialTheme.typography.displayMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        WindowType.Medium -> {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Button(onClick = onNavigateToLogin) {
                            Text(text = "Login")
                        }
                        Text(
                            text = "SETTINGS",
                            fontSize = MaterialTheme.typography.displayMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }
                }
            }
        }
        WindowType.Expanded -> {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Yellow),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Button(onClick = onNavigateToLogin) {
                            Text(text = "Login")
                        }
                        Text(
                            text = "SETTINGS",
                            fontSize = MaterialTheme.typography.displayMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                    }
                }
            }
        }
    }


}

/**
 * Preview for the SettingsScreen
 */
@Composable
@Preview
fun SettingsScreenPreview(windowSize: WindowSize = rememberWindowSize()) {
    val navController: NavHostController = rememberNavController()

    SettingsScreen(
        windowSize = windowSize,
        onNavigateToLogin = { navController.navigate("login")}
    )
}
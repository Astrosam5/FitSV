/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import projet.mobile.kotlin.fitsv.FitSVApplication.Companion.homeScreenText
import projet.mobile.kotlin.fitsv.ui.util.WindowSize
import projet.mobile.kotlin.fitsv.ui.util.WindowType
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize

/**
 * Function used to define UI of the HomeScreen
 */
@Composable
fun HomeScreen(windowSize: WindowSize) {

    when (windowSize.width) {
        WindowType.Compact -> {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = homeScreenText,
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
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
                    Text(
                        text = homeScreenText,
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
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
                    Text(
                        text = homeScreenText,
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }
        }
    }

}


/**
 * Preview for the HomeScreen
 */
@Composable
@Preview
fun HomeScreenPreview(windowSize: WindowSize = rememberWindowSize()) {
    HomeScreen(windowSize = windowSize)
}
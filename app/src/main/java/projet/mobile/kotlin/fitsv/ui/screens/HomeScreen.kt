/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import projet.mobile.kotlin.fitsv.ui.WindowSize
import projet.mobile.kotlin.fitsv.ui.WindowType
import projet.mobile.kotlin.fitsv.ui.rememberWindowSize

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
                        text = "HOME",
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
                        text = "HOME",
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
                        text = "HOME",
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }
        }
        else -> {
            throw IllegalArgumentException("Dp value cannot be negative")
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
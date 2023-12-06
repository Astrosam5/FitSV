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
import androidx.hilt.navigation.compose.hiltViewModel
import projet.mobile.kotlin.fitsv.FitSVApplication.Companion.homeScreenText
import projet.mobile.kotlin.fitsv.ui.util.WindowSize
import projet.mobile.kotlin.fitsv.ui.util.WindowType
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize
import projet.mobile.kotlin.fitsv.ui.viewModel.HomeViewModel

/**
 * Function used to define UI of the HomeScreen
 */
@Composable
fun HomeScreen(
    windowSize: WindowSize,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    when (windowSize.width) {
                        WindowType.Compact -> Color.White
                        WindowType.Medium -> Color.Black
                        WindowType.Expanded -> Color.Yellow
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = homeScreenText,
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color =
                    when (windowSize.width) {
                        WindowType.Compact -> Color.Black
                        WindowType.Medium -> Color.Red
                        WindowType.Expanded -> Color.Blue
                    }
                )
                // Text sensitive to lum level
                val isDark = homeViewModel.isDark
                val lum = if(isDark) "dark" else "light"

                Text(
                    text =  "It is $lum outside",
                    color = if (isDark) Color.Red else Color.Blue
                )
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
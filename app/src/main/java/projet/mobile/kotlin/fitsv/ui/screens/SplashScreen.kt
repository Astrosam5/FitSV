/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import projet.mobile.kotlin.fitsv.R

/**
 * Function that setup the ui of the splashScreen
 * @param modifier: parameter used to setup ui
 */
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo of the App",
        )
    }
}

/**
 * Used for previewing the splashScreen
 */
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(modifier = Modifier.fillMaxSize())
}
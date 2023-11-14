/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.ui.theme.FitSVTheme
import kotlin.time.Duration.Companion.seconds

/**
 * Class SplashScreenActivity
 * Used to make splash screen appear at the lunch of the app
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class SplashScreenActivity : ComponentActivity() {

    /**
     * Function used to define what will be created at setup
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                delay(3.seconds)
                // Lunch MainActivity
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
            }

            FitSVTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

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
    FitSVTheme {
        SplashScreen()
    }
}
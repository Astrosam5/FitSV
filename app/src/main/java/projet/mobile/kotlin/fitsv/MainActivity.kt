/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */

package projet.mobile.kotlin.fitsv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import projet.mobile.kotlin.fitsv.ui.rememberWindowSize
import projet.mobile.kotlin.fitsv.ui.screens.MainScreen
import projet.mobile.kotlin.fitsv.ui.theme.BottomNavBarDemoTheme
import projet.mobile.kotlin.fitsv.ui.theme.FitSVTheme

/**
 * Class MainActivity
 * Used setup the UI of the main screen of the app
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class MainActivity : ComponentActivity() {

    /**
     * Function used to define what will be created at setup
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            FitSVTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }

            BottomNavBarDemoTheme {
                val window = rememberWindowSize()
                MainScreen(windowSize = window)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitSVTheme {
        Greeting("Android")
    }
}
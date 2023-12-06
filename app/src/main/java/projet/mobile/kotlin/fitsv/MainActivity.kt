/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.OutlinedButton
import androidx.wear.compose.material.Text
import dagger.hilt.android.AndroidEntryPoint
import projet.mobile.kotlin.fitsv.ui.theme.BottomNavBarDemoTheme
import projet.mobile.kotlin.fitsv.ui.util.Navigation
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize

/**
 * Class MainActivity
 * Used setup the UI of the main screen of the app
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Function used to define what will be created at setup
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavBarDemoTheme {
                val window = rememberWindowSize()
                Navigation(windowSize = window)

            }
        }
    }

}

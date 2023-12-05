/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import projet.mobile.kotlin.fitsv.FitSVApplication
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.SignInActivity
import projet.mobile.kotlin.fitsv.ui.theme.FitSVTheme
import projet.mobile.kotlin.fitsv.ui.util.WindowSize
import projet.mobile.kotlin.fitsv.ui.util.WindowType
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize
import projet.mobile.kotlin.fitsv.ui.viewModel.SettingsViewModel

/**
 * Function used to define UI of the SettingsScreen
 */
@Composable
fun SettingsScreen(
    windowSize: WindowSize,
    settingViewModel: SettingsViewModel = hiltViewModel()
) {
    FitSVTheme {
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
                    text = stringResource(R.string.settings),
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color =
                    when (windowSize.width) {
                        WindowType.Compact -> Color.Black
                        WindowType.Medium -> Color.Red
                        WindowType.Expanded -> Color.Blue
                    }
                )
                Logging()
            }
        }
    }
}

fun IsLoggedIn() = Firebase.auth.currentUser != null

@Composable
fun Logging(modifier: Modifier = Modifier) {
    var loggedIn by remember { mutableStateOf(IsLoggedIn()) }

    val mContext = LocalContext.current

    if(loggedIn){
        val name = Firebase.auth.currentUser!!.displayName
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {
            Firebase.auth.signOut()
            loggedIn = IsLoggedIn()
        }) {
            Text(text = "Sign Out")
        }
    } else {
        Button(onClick = {
            mContext.startActivity(Intent(mContext, SignInActivity::class.java))
        }) {
            Text(text = "Sign In")
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
    )
}
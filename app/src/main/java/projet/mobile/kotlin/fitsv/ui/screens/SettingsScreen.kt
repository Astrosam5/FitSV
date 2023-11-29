/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import android.Manifest
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import projet.mobile.kotlin.fitsv.FitSVApplication
import projet.mobile.kotlin.fitsv.R
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
    onNavigateToLogin: () -> Unit,
    settingViewModel: SettingsViewModel = hiltViewModel()
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
                if (!FitSVApplication.loginState.logged) {
                    Button(onClick = onNavigateToLogin) {
                        Text(text = stringResource(R.string.login))
                    }
                } else {
                    Button(onClick = { settingViewModel.openAddPicture() }) {
                        Text(text = stringResource(R.string.add_picture))
                    }
                    if (settingViewModel.isOpenAddPicture()) {
                        add_picture()
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun add_picture() {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    if (cameraPermissionState.status.isGranted) {
        Text("Camera permission Granted")
    } else {
        Column {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                "The camera is important for this app. Please grant the permission."
            } else {
                "Camera not available"
            }

            Text(textToShow)
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                cameraPermissionState.launchPermissionRequest()  // TODO see why don't work
            }) {
                Text("Request permission")
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
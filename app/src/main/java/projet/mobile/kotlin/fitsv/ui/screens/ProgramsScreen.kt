/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import projet.mobile.kotlin.fitsv.MyNotification
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.ui.util.WindowSize
import projet.mobile.kotlin.fitsv.ui.util.WindowType
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize

/**
 * Function used to define UI of the ProgramsScreen
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProgramsScreen(windowSize: WindowSize) {

    val mContext = LocalContext.current

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
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.programs),
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color =
                when (windowSize.width) {
                    WindowType.Compact -> Color.Black
                    WindowType.Medium -> Color.Red
                    WindowType.Expanded -> Color.Blue
                }
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val notificationPermissionState = rememberPermissionState(
                    Manifest.permission.POST_NOTIFICATIONS
                )
                val context = LocalContext.current
                OutlinedButton(onClick = {
                    // Camera permission state

                    if (notificationPermissionState.status.isGranted) {
                        val notification = MyNotification(mContext,
                            "Test",
                            "This is a test notification")
                        notification.fireNotification()
                    } else {
                        Toast.makeText(
                            context,
                            "You have to first grant notifications permissions in settings !!",
                            Toast.LENGTH_LONG)
                            .show()
                    }

                }) {
                    Text(text = "Fire Notification", fontSize = 16.sp)
                }
            }
        }
    }
}

/**
 * Preview for the ProgramsScreen
 */
@Composable
@Preview
fun ProgramsScreenPreview(windowSize: WindowSize = rememberWindowSize()) {
    ProgramsScreen(windowSize = windowSize)
}
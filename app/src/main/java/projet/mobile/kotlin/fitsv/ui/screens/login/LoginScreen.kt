/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


/**
 * Function used to define UI of the LoginScreen
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen (
    onNavigateToSingUp: () -> Unit
    // loginViewModel: LoginViewModel = viewModel()
) {
    //val viewModel = hiltViewModel<LoginViewModel>()
    var usernameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            TextField(
                value = usernameText,
                onValueChange = { usernameText = it },
                label = { Text("Username") },
                singleLine = true,
                placeholder = { Text("Username") },
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            TextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                label = { Text("Password") },
                singleLine = true,
                placeholder = { Text("Password") },
            )
            Row (horizontalArrangement = Arrangement.SpaceEvenly ) {
                Button(onClick = onNavigateToSingUp) {
                    Text(text = "Sing-up")
                }
                //Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Button(onClick = { /*TODO Connect*/ }) {
                    Text(text = "Connect")
                }

            }

        }
    }


}

/**
 * Preview for the LoginScreen
 */
@Composable
@Preview
fun LoginScreenPreview() {
    val navController: NavHostController = rememberNavController()
    LoginScreen(onNavigateToSingUp = {navController.navigate("sing_up")})
}
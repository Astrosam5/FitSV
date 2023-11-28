/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import projet.mobile.kotlin.fitsv.FitSVApplication
import projet.mobile.kotlin.fitsv.FitSVApplication.Companion.loginState
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import projet.mobile.kotlin.fitsv.ui.routes.SettingsRoutes
import projet.mobile.kotlin.fitsv.ui.states.LoginState
import projet.mobile.kotlin.fitsv.ui.states.ResourcesState
import projet.mobile.kotlin.fitsv.ui.viewModel.LoginViewModel


const val TAG = "LoginScreen"

/**
 * Function used to define UI of the LoginScreen
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen (
    onNavigateToSingUp: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHomeScreen: () -> Unit
) {
    val userListRes by loginViewModel.userList.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        when(userListRes) {
            is ResourcesState.Loading -> {
                Log.d(TAG, "Inside Loading")
                Loader()
            }

            is ResourcesState.Success -> {
                val response = (userListRes as ResourcesState.Success).data
                Log.d(TAG, "Inside Success\n" +
                        "Nb of user ${response.size}")
                if (response.isNotEmpty()) {
                    ShowConnexionColumn(
                        onNavigateToSingUp = onNavigateToSingUp,
                        onNavigateToHomeScreen = onNavigateToHomeScreen,
                        loginViewModel = loginViewModel,
                        listUser = response
                    )
                } else {
                    Log.d(TAG, "No user")
                    Column (modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = stringResource(R.string.no_user_registered),
                            color = Color.Red,
                        )
                        Button(onClick = onNavigateToSingUp) {
                            Text(text = stringResource(R.string.sing_up))
                        }
                    }
                }
            }

            is ResourcesState.Error -> {
                Log.d(TAG, "Inside Error")
                Column (modifier = Modifier.fillMaxWidth()){
                    Text(
                        text = stringResource(id = R.string.internal_error),
                        color = Color.Red,
                    )

                }

            }
        }

    }
}

@Composable
fun ShowConnexionColumn(
    onNavigateToSingUp: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    listUser: List<UserModel>) {
    var usernameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    Column {
        // ------------------------ Username ------------------------
        TextField(
            value = usernameText,
            onValueChange = { usernameText = it },
            label = { Text(stringResource(R.string.username)) },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.username)) },
        )

        // ------------------------ Passwords ------------------------
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        var passwordVisible by remember { mutableStateOf(false) }
        TextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text(stringResource(R.string.password)) },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.password)) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                // Toggle button to hide or display password
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
        Row (horizontalArrangement = Arrangement.SpaceEvenly ) {
            Button(onClick = onNavigateToSingUp) {
                Text(text = "Sing-up")
            }
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            Button(onClick = {
                loginViewModel.connexion(usernameText, passwordText, listUser, onNavigateToHomeScreen)
                if (loginState.logged && loginState.user != null) {
                    loginViewModel.deleteAllUser()
                    loginViewModel.saveUser(loginState.user!!)
                }

            }) {
                Text(text = stringResource(R.string.connect))
            }
        }

        // ------------------------ Alerts dialogs ------------------------
        when {
            loginViewModel.getAlertEmpty() -> {
                AlertDialog(
                    onDismissRequest = { loginViewModel.closeAlertEmpty() },
                    buttons = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { loginViewModel.closeAlertEmpty() }
                        ) { Text(stringResource(R.string.dismiss)) }
                    },
                    text =  { Text(text = stringResource(R.string.username_and_password_must_be_filled)) }
                )
            }
            loginViewModel.getAlertWrongPassword() -> {
                AlertDialog(
                    onDismissRequest = { loginViewModel.closeAlertWrongPassword() },
                    buttons = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { loginViewModel.closeAlertWrongPassword() }
                        ) { Text(stringResource(R.string.dismiss)) }
                    },
                    text =  { Text(text = stringResource(R.string.password_is_incorrect)) }
                )
            }
            loginViewModel.getAlertUnknownUser() -> {
                AlertDialog(
                    onDismissRequest = { loginViewModel.closeAlertUnknownUser() },
                    buttons = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { loginViewModel.closeAlertUnknownUser() }
                        ) { Text(stringResource(R.string.dismiss)) }
                    },
                    text =  { Text(text = stringResource(R.string.unknown_user)) }
                )
            }
        }
    }
}



@Composable
fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp),
        color = Color.Blue
    )
}



/**
 * Preview for the LoginScreen
 */
@Composable
@Preview
fun LoginScreenPreview() {
    val navController: NavHostController = rememberNavController()
    LoginScreen(onNavigateToSingUp = {navController.navigate(SettingsRoutes.SingUp.route)}) {
        navController.navigate(
            projet.mobile.kotlin.fitsv.ui.routes.BottomBarRoutes.Home.route
        )
    }
}
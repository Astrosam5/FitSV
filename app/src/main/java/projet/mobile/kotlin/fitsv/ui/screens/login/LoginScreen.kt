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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import projet.mobile.kotlin.fitsv.FitSVApplication
import projet.mobile.kotlin.fitsv.FitSVApplication.Companion.loginState
import projet.mobile.kotlin.fitsv.domain.model.UserModel
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
                    // TODO Prompt error and propose to sing-up
                }
            }

            is ResourcesState.Error -> {
                Log.d(TAG, "Inside Error")
                // TODO Prompt error about internet connexion

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
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            Button(onClick = {
                connexion(usernameText, passwordText, listUser, onNavigateToHomeScreen)
                if (loginState.logged && loginState.user != null) {
                    loginViewModel.deleteAllUser()
                    loginViewModel.saveUser(loginState.user!!)
                }

            }) {
                Text(text = "Connect")
            }
        }
    }
}



@Composable
fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp).padding(10.dp),
        color = Color.Blue
    )
}


fun connexion(
    username: String,
    password: String,
    listUser: List<UserModel>,
    onNavigateToHomeScreen: () -> Unit
) {
    // Username transformation
    val cleanUsername = username.lowercase().trim()
    // Search user in list
    var userFound: UserModel? = null
    for (user in listUser) {
        if (user.name.lowercase() == cleanUsername) {
            userFound = user
        }
    }
    // Password transformation
    val cleanPassword = password.trim()

    if (userFound != null) {
        if (userFound.password == cleanPassword) {
            loginState = LoginState(userFound, true)
            val homeText = "Hello ${loginState.user?.name ?: ""}"
            FitSVApplication.homeScreenText = homeText
            onNavigateToHomeScreen()
        } else {
             // TODO Prompt password is wrong
        }
    } else {
        // TODO Prompt error message
    }
}

/**
 * Preview for the LoginScreen
 */
@Composable
@Preview
fun LoginScreenPreview() {
    val navController: NavHostController = rememberNavController()
    LoginScreen(onNavigateToSingUp = {navController.navigate("sing_up")}) {
        navController.navigate(
            projet.mobile.kotlin.fitsv.ui.routes.BottomBarRoutes.Home.route
        )
    }
}
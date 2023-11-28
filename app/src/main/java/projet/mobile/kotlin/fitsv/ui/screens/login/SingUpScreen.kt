/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
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
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.domain.model.ObjectId
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import projet.mobile.kotlin.fitsv.ui.routes.BottomBarRoutes
import projet.mobile.kotlin.fitsv.ui.states.LoginState
import projet.mobile.kotlin.fitsv.ui.states.ResourcesState
import projet.mobile.kotlin.fitsv.ui.viewModel.SingUpViewModel

/**
 * Function used to define UI of the SingUpScreen
 */
@Composable
fun SingUpScreen(
    onNavigateToHomeScreen: () -> Unit,
    singUpViewModel: SingUpViewModel = hiltViewModel(),
) {
    val userListRes by singUpViewModel.userList.collectAsState()
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
                    SingUpColumn(
                        onNavigateToHomeScreen = onNavigateToHomeScreen,
                        singUpViewModel = singUpViewModel,
                        listUser = response
                    )
                } else {
                    Log.d(TAG, "No user")
                    SingUpColumn(
                        onNavigateToHomeScreen = onNavigateToHomeScreen,
                        singUpViewModel = singUpViewModel,
                        listUser = listOf() // send empty list
                    )
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
fun SingUpColumn(
    onNavigateToHomeScreen: () -> Unit,
    singUpViewModel: SingUpViewModel,
    listUser: List<UserModel>
) {

    Column {
        // ------------------------ Check Username ------------------------
        var usernameText by remember { mutableStateOf("") }
        // By default username is empty, it does not exist
        var usernameIsInit by remember { mutableStateOf(false) }
        var usernameAlreadyExist by remember { mutableStateOf(false) }
        var usernameIsBlank by remember { mutableStateOf(true) }
        var usernameIsOk by remember { mutableStateOf(false) }
        TextField(
            value = usernameText,
            onValueChange = {
                usernameText = it
                usernameIsInit = true
                usernameAlreadyExist = singUpViewModel.doesUsernameExist(usernameText, listUser)
                usernameIsBlank = usernameText.isBlank()
                usernameIsOk = singUpViewModel.doesUsernameFormatIsOk(usernameText)
                            },
            label = { Text(stringResource(R.string.username)) },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.username)) },
        )
        // TODO see why don't work
        if (usernameIsInit && (usernameIsBlank || usernameAlreadyExist || !usernameIsOk )) {
            Text(
                text = when {
                    usernameIsBlank -> stringResource(R.string.username_must_be_filled)
                    !usernameIsOk -> stringResource(R.string.username_can_only_contain)
                    usernameAlreadyExist -> stringResource(R.string.this_username_already_exist)
                    else -> stringResource(R.string.internal_error)
                },
                maxLines = 1,
                color = Color.Red,
                modifier = Modifier.size(5.dp)
            )
        }

        // ------------------------ Check password ------------------------
        var passwordText by remember { mutableStateOf("") }
        // By default no password
        var passwordIsInit by remember { mutableStateOf(false) }
        var passwordIsBlank by remember { mutableStateOf(true) }
        var passwordIsOk by remember { mutableStateOf(false) }
        var passwordVisible by remember { mutableStateOf(false) }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        TextField(
            value = passwordText,
            onValueChange = {
                passwordText = it
                passwordIsInit = true
                passwordIsBlank = passwordText.isBlank()
                passwordIsOk = singUpViewModel.doesPasswordFormatIsOk(passwordText)
                            },
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
        if (passwordIsInit && (passwordIsBlank || !passwordIsOk)) {
            Text(
                text = when {
                    passwordIsBlank -> stringResource(R.string.password_must_be_filled)
                    !passwordIsOk -> stringResource(R.string.password_can_only_contain)
                    else -> stringResource(R.string.internal_error)
                },
                color = Color.Red,
                maxLines = 1,
                modifier = Modifier.size(5.dp)
            )

        }

        // ------------------------ Check password confirmation ------------------------
        var passwordConfirmationText by remember { mutableStateOf("") }
        var passwordCorrespond by remember { mutableStateOf(false) }
        var passwordConfVisible by remember { mutableStateOf(false) }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        TextField(
            value = passwordConfirmationText,
            onValueChange = {
                passwordConfirmationText = it
                passwordCorrespond = singUpViewModel.doesPasswordCorrespond(passwordText, passwordConfirmationText)
                            },
            label = { Text(stringResource(R.string.password_confirmation)) },
            singleLine = true,
            placeholder = { Text(stringResource(R.string.password_confirmation)) },
            visualTransformation = if (passwordConfVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordConfVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localized description for accessibility services
                val description = if (passwordConfVisible) "Hide password" else "Show password"

                // Toggle button to hide or display password
                IconButton(onClick = {passwordConfVisible = !passwordConfVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
        if (passwordIsInit && !passwordCorrespond) {
            Text(
                text = stringResource(R.string.password_must_correspond),
                color = Color.Red,
                maxLines = 1,
                modifier = Modifier.size(5.dp)
            )
        }

        // ------------------------ Button ------------------------
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        var openAlertEmpty by remember { mutableStateOf(false) }
        var openAlertUsername by remember { mutableStateOf(false) }
        var openAlertPassword by remember { mutableStateOf(false) }
        Button(onClick = {
            when {
                (!usernameIsInit || usernameIsBlank || !passwordIsInit || passwordIsBlank) -> {
                    openAlertEmpty = true
                }
                (usernameAlreadyExist || !usernameIsOk) -> {
                    openAlertUsername = true
                }
                (!passwordCorrespond || !passwordIsOk) -> {
                    openAlertPassword = true
                }
                else -> {
                    // create user
                    val newUser = UserModel(
                        name=usernameText.trim().lowercase(),
                        password=passwordText.trim(),
                        id=ObjectId(""))
                    // add user to API
                    singUpViewModel.addUserOnAPI(newUser)
                    singUpViewModel.saveUserToDB(newUser)

                    // log user
                    FitSVApplication.loginState = LoginState(newUser, true)
                    val homeText = "Hello ${FitSVApplication.loginState.user?.name ?: ""}"
                    FitSVApplication.homeScreenText = homeText
                    onNavigateToHomeScreen()
                }
            }
        }) {
            Text(text = stringResource(R.string.sing_up))
        }

        // ------------------------ Alerts dialogs ------------------------
        when {
            openAlertEmpty -> {
                AlertDialog(
                    onDismissRequest = { openAlertEmpty = false },
                    buttons = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { openAlertEmpty = false }
                        ) { Text(stringResource(R.string.dismiss)) }
                    },
                    text =  { Text(text = stringResource(R.string.username_and_password_must_be_filled)) }
                )
            }
            openAlertUsername -> {
                AlertDialog(
                    onDismissRequest = { openAlertUsername = false },
                    buttons = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { openAlertUsername = false }
                        ) { Text(stringResource(R.string.dismiss)) }
                    },
                    text =  { Text(text = stringResource(R.string.username_is_incorrect)) }
                )
            }
            openAlertPassword -> {
                AlertDialog(
                    onDismissRequest = { openAlertPassword = false },
                    buttons = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { openAlertPassword = false }
                        ) { Text(stringResource(R.string.dismiss)) }
                    },
                    text =  { Text(text = stringResource(R.string.password_is_incorrect)) }
                )
            }
        }
    }
}


/**
 * Preview for the SingUpScreen
 */
@Composable
@Preview
fun SingUpScreenPreview() {
    val navController: NavHostController = rememberNavController()
    SingUpColumn(
        onNavigateToHomeScreen = {navController.navigate(BottomBarRoutes.Home.route)},
        singUpViewModel = hiltViewModel(),
        listUser = listOf() // send empty list
    )
}
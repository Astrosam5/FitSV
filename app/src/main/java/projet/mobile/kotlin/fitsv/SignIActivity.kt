package projet.mobile.kotlin.fitsv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import projet.mobile.kotlin.fitsv.ui.theme.FitSVTheme


class SignIActivity : AppCompatActivity() {

    private val singIn: ActivityResultLauncher<Intent> =
        registerForActivityResult(FirebaseAuthUIActivityResultContract(), this::onSignInResult)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitSVTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Started Sing in activity")

        if(Firebase.auth.currentUser != null) {
            Log.d(TAG, "As a current user")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        val singInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setLogo(R.mipmap.ic_launcher)
            .setAvailableProviders(listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.AnonymousBuilder().build(),
            ))
            .build()

        Log.d(TAG, "Goes to main activity")
        singIn.launch(singInIntent)
        Log.d(TAG, "Ended Sing in activity")
    }

    fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if(result.resultCode == RESULT_OK) {
            Log.d(TAG, "Sing in successful")
            return
        }

        Toast.makeText(
            this,
            "There was an error signing in",
            Toast.LENGTH_LONG
        ).show()

        val response = result.idpResponse
        if(response == null) {
            Log.w(TAG, "Sign In Cancelled")
        } else {
            Log.w(TAG, "Sign in error:", response.error)
        }

    }

    companion object {
        const val TAG = "SignInActivity"
    }
}




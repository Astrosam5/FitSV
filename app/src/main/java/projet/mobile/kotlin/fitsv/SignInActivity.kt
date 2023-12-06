package projet.mobile.kotlin.fitsv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import projet.mobile.kotlin.fitsv.ui.theme.FitSVTheme

class SignInActivity : ComponentActivity() {

    private val signIn: ActivityResultLauncher<Intent> =
        registerForActivityResult(FirebaseAuthUIActivityResultContract(), this::OnSignInResult)
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

        if (Firebase.auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setLogo(R.mipmap.ic_launcher)
            .setAvailableProviders(listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.AnonymousBuilder().build()
            ))
            .build()
        signIn.launch(signInIntent)
    }

    fun OnSignInResult(resultat: FirebaseAuthUIAuthenticationResult){
        if(resultat.resultCode == RESULT_OK){
            Log.d(TAG, "Sign in successful !")
            return
        }

        Toast.makeText(
            this,
            "There was an error signing in",
            Toast.LENGTH_LONG
        ).show()

        val response = resultat.idpResponse
        if(response == null){
            Log.w(TAG, "Sign in canceled")
        } else {
            Log.w(TAG, "Sign in result :", response.error)
        }
    }
    companion object{
        private const val TAG = "SignInActivity"
    }

}

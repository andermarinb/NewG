package com.app.newg

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.app.newg.navigation.AppNavigation
import com.app.newg.logIn.LoginViewModel
import com.app.newg.ui.theme.NewGTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : LoginViewModel by viewModels()
            val isLoading by viewModel.isLoading().observeAsState(false)
            val hasError by viewModel.hasGoogleError().observeAsState(false)

            NewGTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (hasError){
                        LoginErrorPopup {
                            viewModel.clearErrors()
                        }
                    }
                    AppNavigation( LoginViewModel())
                    }
                }
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...)
            if (requestCode == 1) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val viewModel : LoginViewModel by viewModels()
                val task :Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                viewModel.finishLogin(task)
            }
        }
    }

    @Composable
    private fun LoginErrorPopup(onDismiss: () -> Unit){
        AlertDialog(
            onDismissRequest = onDismiss,
            text = {
                Text(
                    text = "No se pudo conectar",
                    style = MaterialTheme.typography.body2
                )
            },
            confirmButton = {
                TextButton(onClick = onDismiss){
                    Text(text = "CERRAR")
                }
            }
        )
    }


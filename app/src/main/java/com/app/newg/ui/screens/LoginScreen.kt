package com.app.newg.ui.screens

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.newg.MainActivity
import com.app.newg.R
import com.app.newg.logIn.LoginViewModel
import com.app.newg.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController
) {
    navController.enableOnBackPressed(false)
    val activity = LocalContext.current as MainActivity
    val viewModel: LoginViewModel by activity.viewModels()
    val loggedUser by viewModel.loggedUser().observeAsState(null)
    val logged by viewModel.logged().observeAsState(false)
    if (loggedUser != null && !logged) {
        loggedUser!!.displayName?.let {
            PopUpLogin(it) {
                viewModel.logIn()
                navController.navigate("MenuInicio")

            }
        }
    }
    Scaffold(
        topBar= { TopBar(pageName = "App", navController = navController, backbutton = false)},
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(modifier = Modifier.fillMaxSize(),painter = painterResource(id =R.drawable.banner), contentDescription = null)

        }
        Logeate(navController, viewModel, activity)
    }

}

@Composable
fun Logeate(navController: NavController, viewModel: LoginViewModel, activity: MainActivity) {
    val isLoading by viewModel.isLoading().observeAsState(false)
    val googleError by viewModel.googleError().observeAsState("")
    val hasGoogleError by viewModel.hasGoogleError().observeAsState(false)
    val logoG = painterResource(id = R.drawable.gifticon)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            stringResource(id = R.string.login_titule),
            style = MaterialTheme.typography.h3
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            painter = logoG, contentDescription = null
        )

        ButtonG(text = "Login con Google", icon = R.drawable.googleaccess) {
            viewModel.loginWithGoogle(activity)
        }

        if (hasGoogleError) {
            Text(text = googleError, color = Color.Magenta)
        }
        if(isLoading){
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ButtonG(text: String, icon: Int?, onClick: () -> Unit) {
    Button(onClick =  onClick,
        modifier = Modifier.width(300.dp)
    ) {
        if(icon != null){
            Icon(painter = painterResource(id = R.drawable.googleaccess), contentDescription = "")
            Spacer(modifier = Modifier)
        }
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun PopUpLogin(name: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text= {
            Text(
                text = name,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Next")
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

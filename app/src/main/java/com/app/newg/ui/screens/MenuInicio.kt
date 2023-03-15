package com.app.newg.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MenuInicio(navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color(0xFFADD5DA),
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
                .padding(start = 10.dp)
                .padding(end = 10.dp)

        ) {
            Text(
                text = "Regalos",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,

                )

            Spacer(modifier = Modifier.size(60.dp))

            Button(
                onClick = {
                    navController.navigate("SaveG")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF761CDD),
                    contentColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 16.dp
                )
            )
            {
                Text(text = "Nuevo Regalo")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    navController.navigate("Updateg")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF761CDD),
                    contentColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 16.dp
                )
            )
            {
                Text(text = "Modificar regalo")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    navController.navigate("DeleteG")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF761CDD),
                    contentColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 16.dp
                )
            )
            {
                Text(text = "Borrar regalo")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    navController.navigate("SearchG")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF761CDD),
                    contentColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 16.dp
                )
            )
            {
                Text(text = "Buscar regalo")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Spacer(modifier = Modifier.padding(25.dp))
            Row(modifier = Modifier
                .padding(20.dp)) {
                Button(
                    onClick = {
                        navController.navigate("LoginScreen")
                    },colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF761CDD),
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.medium,
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 16.dp
                    )) {
                    Text(text = "Cerrar Sesión")
                }
            }
        }
    }
}
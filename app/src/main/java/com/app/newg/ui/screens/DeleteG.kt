package com.app.newg.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun DeleteG(navController: NavController){


    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "gifts"
    var idG by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 86.dp)
            .padding(start= 8.dp)
            .padding(end= 8.dp)

    ) {

        Text(
            text = "Eliminar el regalo",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(
            value = idG,
            onValueChange = { idG = it },
            label = { Text("Id para borrar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(4.dp))

        var mensaje_borrado by remember { mutableStateOf("") }

        Button(

            onClick = {
                if (idG.isNotBlank()) {
                    db.collection(nombre_coleccion)
                        .document(idG)
                        .delete()
                        .addOnSuccessListener {
                            mensaje_borrado = "Borrado correcto"
                            idG = ""
                        }
                        .addOnFailureListener {
                            mensaje_borrado = "Imposible borrar"
                            idG = ""
                        }
                }
            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Borrar")


        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = mensaje_borrado)
    }

}


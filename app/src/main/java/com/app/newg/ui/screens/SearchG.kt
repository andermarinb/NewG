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
fun SearchG(navController: NavController){

    var nombre_coleccion = "gifts"
    val db = FirebaseFirestore.getInstance()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 86.dp)
            .padding(start = 8.dp)
            .padding(end = 8.dp)
    ) {

        Text(
            text = "Buscar regalos por ID",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(18.dp))

        var datos by remember { mutableStateOf("") }
        var nombreG by remember { mutableStateOf("") }
        var precioG by remember { mutableStateOf("") }

        var idG_busqueda by remember { mutableStateOf("") }
        val field_busqueda ="idG"
        OutlinedTextField(
            value = idG_busqueda,
            onValueChange = { idG_busqueda = it },
            label = { Text("Introduce el ID para buscar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = {
                datos = ""
                nombreG = ""
                precioG = ""

                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection(nombre_coleccion)
                    .whereEqualTo(field_busqueda,idG_busqueda)
                    .get()

                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            //Para crear un HashMap con todos los datos
                            datos += "${encontrado.id}: ${encontrado.data}\n"

                            //Para crear un HashMap con todos los datos
                            nombreG += encontrado["nombreG"].toString()
                            precioG += encontrado["precioG"].toString()
                        }

                        if (datos.isEmpty()) {
                            datos = "No hay datos"
                        }
                    }
                    .addOnFailureListener { resultado ->
                        datos = "La conexión a FireStore no se ha podido completar"
                    }
            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Cargar")
        }

        Spacer(modifier = Modifier.size(8.dp))

        //Se muestra el resultado
        Text (text = "Nombre: " + nombreG)
        Text (text = "Precio: " + precioG)
    }
}
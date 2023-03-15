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
fun UpdateG(navController: NavController){
    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "gifts"
    var idG by remember{ mutableStateOf("") }
    var nombreG by remember { mutableStateOf("")}
    var precioG by remember{ mutableStateOf("")}
    var descripcionG by remember{ mutableStateOf("")}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 86.dp)
            .padding(start= 8.dp)
            .padding(end= 8.dp)

    ) {

        Text(
            text = "Modificar el regalo",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(
            value = idG,
            onValueChange = { idG = it },
            label = { Text("Id para modificar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = nombreG,
            onValueChange = { nombreG = it },
            label = { Text("Nombre a modificar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = precioG,
            onValueChange = { precioG = it },
            label = { Text("Precio a modificar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = descripcionG,
            onValueChange = { descripcionG = it },
            label = { Text("Descripcion a modificar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )


        Spacer(modifier = Modifier.size(4.dp))

        val dato = hashMapOf(
            "idG" to idG.toString(),
            "nombreG" to nombreG.toString(),
            "precioG" to precioG.toString(),
            "descripcionG" to descripcionG.toString()
        )

        var mensaje_confirmacion by remember { mutableStateOf("") }

        Button(

            onClick = {
                if (idG.isNotBlank()) {
                    db.collection(nombre_coleccion)
                        .document(idG)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje_confirmacion = "Modificado correcto"
                            idG = ""
                            nombreG =""
                            precioG =""
                            descripcionG =""
                        }
                        .addOnFailureListener {
                            mensaje_confirmacion = "Imposible modificar"
                            idG = ""
                            nombreG =""
                            precioG =""
                            descripcionG =""
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

            Text(text = "Modificar")


        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = mensaje_confirmacion)
    }

}
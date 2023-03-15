package com.app.newg.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SaveG(navController: NavController){
    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "gifts"
    var idG by remember{ mutableStateOf("")}
    var nombreG by remember { mutableStateOf("")}
    var precioG by remember{ mutableStateOf("")}
    var descripcionG by remember{ mutableStateOf("")}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray),
        elevation = 112.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.Black)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 86.dp)
                .padding(start= 8.dp)
                .padding(end= 8.dp)
                .background(Color.Gray)

        ) {

            Text(color = Color.Black,
                text = "Guardar regalo",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(18.dp))

            OutlinedTextField(
                value = idG,
                onValueChange = { idG = it },
                label = { Text("Introduce ID") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(4.dp))

            OutlinedTextField(
                value = nombreG,
                onValueChange = { nombreG = it },
                label = { Text("Introduce el nombre de regalo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(4.dp))

            OutlinedTextField(
                value = precioG,
                onValueChange = { precioG = it },
                label = { Text("Introduce precio") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(4.dp))

            OutlinedTextField(
                value = descripcionG,
                onValueChange = { descripcionG = it },
                label = { Text("Describelo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(8.dp))


            val dato = hashMapOf(
                "idG" to idG.toString(),
                "nombreG" to nombreG.toString(),
                "precioG" to precioG.toString(),
                "descripcionG" to descripcionG.toString()
            )

            var mensaje_confirmacion by remember { mutableStateOf("") }

            Button(
                onClick = {
                    db.collection(nombre_coleccion)
                        .document(idG)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje_confirmacion ="Guardado satisfactoriamente"
                            idG =""
                            nombreG=""
                            precioG=""
                            descripcionG=""
                        }
                        .addOnFailureListener {
                            mensaje_confirmacion ="Error al guardar"
                            idG =""
                            nombreG=""
                            precioG=""
                            descripcionG=""
                        }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Green,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Guardar")


            }
            Spacer(modifier = Modifier.size(3.dp))
            Text(text = mensaje_confirmacion)
        }

    }

}
package com.example.superadmin


import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Notificaciones: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            NotificationBar()
            SearchBar()
            EmailList()
        }
    }

    @Composable
    fun HeaderSection() {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top // Cambiar a Alignment.Top
        ) {
            // Logo SENA
            Image(
                painter = painterResource(id = R.drawable.logo_sena),
                contentDescription = "Logo SENA",
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            // Logo Etapa Productiva
            Image(
                painter = painterResource(id = R.drawable.logo_etapaproductiva),
                contentDescription = "Logo Etapa Productiva",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Textos
            Column {
                androidx.compose.material.Text(
                    "Etapa\nProductiva",
                    fontSize = 13.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier
                        .padding(top = 6.dp) // Ajusta la distancia hacia abajo
                        .offset(x = (-5).dp) // Desplaza el texto hacia la izquierda
                )
                Spacer(modifier = Modifier.height(15.dp)) // Espaciador para bajar el texto "Centro de Comercio y Servicios"
                androidx.compose.material.Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 14.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier.offset(x = (-30).dp) // Desplazar 30 dp hacia la izquierda
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Para empujar el icono del usuario a la derecha

            // Icono de usuario
            Image(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = "User Icon",
                modifier = Modifier.size(45.dp)
            )
        }
    }

    @Composable
    fun NotificationBar() {
        val context = LocalContext.current // Obtener el contexto local

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFF009E00)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.notificaciones_icon),
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Al hacer clic en el icono de notificaciones, iniciar NotificationActivity
                        context.startActivity(Intent(context, Notificaciones::class.java))
                    },
                colorFilter = ColorFilter.tint(Color.White) // Cambia el color a blanco
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono desplegable a la izquierda del campo de búsqueda
            IconButton(onClick = { /* Acción del desplegable */ }) {
                Image(
                    painter = painterResource(id = R.drawable.menu1),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(45.dp)
                )
            }

            // Campo de búsqueda
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp, end = 6.dp) // Añade espacio a los lados del TextField
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(2.dp)), // Fondo gris con esquinas redondeadas
                shape = RoundedCornerShape(16.dp), // Ajusta el tamaño de los bordes redondeados
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedIndicatorColor = Color.Transparent, // Sin indicador al enfocar
                    unfocusedIndicatorColor = Color.Transparent // Sin indicador al no enfocar
                )
            )

            // Botón de redactar
            IconButton(
                onClick = { // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                    startActivity(Intent(this@Notificaciones, "Redactar"::class.java)) },
                modifier = Modifier.size(25.dp) // Tamaño del icono
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mas),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }

    @Composable
    fun EmailList() {
        var selectedTab by remember { mutableStateOf("Recibidos") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Fila para el botón de Redactar y las pestañas

            LazyColumn {
                items(7) {
                    EmailItem(
                        title = "Título de la Notificación",
                        subject = "Asunto de la Notificación",
                        date = "Fecha"
                    )
                }
            }
        }
    }

    @Composable
    fun EmailItem(title: String, subject: String, date: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(2.dp) // Esquinas ligeramente redondeadas
                )
                .clickable {  // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                    startActivity(Intent(this@Notificaciones, Email::class.java)) }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f) // Para que ocupe el espacio disponible
            ) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(subject, color = Color.Gray, fontSize = 12.sp)
                Text(date, color = Color.Gray, fontSize = 11.sp)
            }

            // Imagen como botón
            IconButton(onClick = { /* Acción al hacer clic en la imagen */ }) {
                Image(
                    painter = painterResource(id = R.drawable.papelera),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}



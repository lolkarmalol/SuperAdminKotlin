package com.example.superadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Perfil_SuperAdmin : ComponentActivity() { // Nombre de la clase actualizado
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
            MainContent()  // Sección principal para mostrar el perfil
        }
    }

    @Composable
    fun HeaderSection() {
        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo SENA
            Image(
                painter = painterResource(id = R.drawable.logo_sena),
                contentDescription = "Logo SENA",
                modifier = Modifier
                    .size(70.dp)
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
            )
            Spacer(modifier = Modifier.width(10.dp))

            // Logo Etapa Productiva
            Image(
                painter = painterResource(id = R.drawable.logo_etapaproductiva),
                contentDescription = "Logo Etapa Productiva",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Textos
            Column(
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            ) {
                androidx.compose.material.Text(
                    "Etapa\nProductiva",
                    fontSize = 13.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                androidx.compose.material.Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 14.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier.offset(x = (-30).dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Para empujar el icono del usuario a la derecha

            // Icono de usuario
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clickable { expanded = !expanded } // Cambia el estado al hacer clic
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_icon),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(45.dp)
                )

                // Menú desplegable
                androidx.compose.material.DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(240.dp) // Ajusta el ancho del menú según sea necesario
                ) {
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, Perfil_SuperAdmin::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Ver perfil")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Inicio")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, Configuracion::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Configuración")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, "Permisos"::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Permisos")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, AdministradoresActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Administradores")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, InstructorActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Instructores")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, ApprenticeActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Aprendices")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, GraphicActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Gráficas")
                    }
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        // Implementar lógica de cierre de sesión
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Cerrar sesión")
                    }
                }
            }
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

    @Composable
    fun MainContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Añade scroll si es necesario
        ) {
            // Sección de información del perfil del administrador
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.aprendiz), // Reemplazar con la imagen del administrador
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )

                UserProfileField(label = "Nombres", value = "Carolina")
                UserProfileField(label = "Apellidos", value = "Díaz")
                UserProfileField(label = "N° identificación", value = "1060435758")
                UserProfileField(label = "N° ficha", value = "2354781")
                UserProfileField(label = "Correo Electrónico", value = "carolinadiaz@gmail.com")
                UserProfileField(label = "Departamento", value = "Cauca")
                UserProfileField(label = "Municipio", value = "Popayán")
                UserProfileField(label = "Género", value = "Femenino")
                UserProfileField(label = "Rol", value = "Administrador")
                UserProfileField(label = "Sede", value = "CCyS")

                // Sección de botones Bitácora y Visita en la misma fila
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly // Espacio entre los botones
                ) {
                    // Aquí puedes agregar los botones si es necesario
                }

            }
        }
    }

    @Composable
    fun UserProfileField(label: String, value: String) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(
                text = value,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)) // Borde gris redondeado
                    .padding(8.dp)
            )
        }
    }

    @Composable
    fun ActionButton(iconRes: Int, text: String, onClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(80.dp)
            )
            Text(text = text)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}

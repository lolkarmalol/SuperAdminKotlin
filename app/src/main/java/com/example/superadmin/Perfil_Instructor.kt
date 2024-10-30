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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

class Perfil_instructor : ComponentActivity() {
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
            MainContent()  // Include this to render the profile content
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
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(240.dp) // Ajusta el ancho del menú según sea necesario
                ) {
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, Perfil_SuperAdmin::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Ver perfil")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Inicio")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, Configuracion ::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Configuración")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, "Permisos"::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Permisos")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, AdministradoresActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Administradores")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, InstructorActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Instructores")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, ApprenticeActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Aprendices")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, GraphicActivity::class.java))
                        expanded = false
                    }) {
                        androidx.compose.material.Text("Gráficas")
                    }
                    DropdownMenuItem(onClick = {
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
        val context = LocalContext.current

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
                        context.startActivity(Intent(context, Notificaciones::class.java))
                    },
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }

    @Composable
    fun MainContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.instructor),
                    contentDescription = "Instructor Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )

                UserProfileField(label = "Nombres", value = "Carlos")
                UserProfileField(label = "Apellidos", value = "Rodríguez")
                UserProfileField(label = "N° identificación", value = "1036452789")
                UserProfileField(label = "Correo Electrónico", value = "carlosrodriguez@sena.edu.co")
                UserProfileField(label = "Departamento", value = "Valle del Cauca")
                UserProfileField(label = "Municipio", value = "Cali")
                UserProfileField(label = "Área de Formación", value = "Desarrollo de Software")
                UserProfileField(label = "Nombre del Programa", value = "Instructor ADSO")
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
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
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
        MainContent()
    }
}

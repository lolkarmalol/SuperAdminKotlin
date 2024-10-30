package com.example.superadmin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superadmin.Perfil_instructor
import com.example.superadmin.R


class Configuracion : ComponentActivity() {
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
            SettingsScreen()

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFF009E00)), // Verde
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.notificaciones),
                contentDescription = "Notification Icon",
                modifier = Modifier.size(60.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
    @Composable
    fun SettingsScreen() {
        // Variables para almacenar el texto de los campos
        var currentPassword by remember { mutableStateOf("") }
        var newPassword by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var resultMessage by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            // Título "Configuración"
            Text(
                text = "Configuración",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )

            // Tarjeta de "Cambio de Contraseña"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White) // Fondo blanco para la tarjeta
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Cambio de Contraseña",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Campo de Contraseña Actual
                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Contraseña Actual") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo de Nueva Contraseña
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nueva Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo de Confirmación de Contraseña
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar Nueva Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para actualizar la contraseña
                    Button(
                        onClick = {
                            // Verificar si las contraseñas coinciden
                            resultMessage = if (newPassword == confirmPassword) {
                                "Actualización exitosa"
                            } else {
                                "Las contraseñas no coinciden"
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009E00))
                    ) {
                        Text("Actualizar Contraseña", color = Color.White)
                    }

                    // Mostrar el mensaje de resultado
                    if (resultMessage.isNotEmpty()) {
                        Text(
                            text = resultMessage,
                            color = if (resultMessage == "Actualización exitosa") Color.Green else Color.Red,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }

}
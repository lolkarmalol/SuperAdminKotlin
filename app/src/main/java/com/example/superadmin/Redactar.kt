package com.example.superadmin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("DEPRECATION")
class Redactar: ComponentActivity() {
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
            ReportScreen()
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
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ReportScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White) // Fondo similar al de la página web
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFF2F3E4C), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Reporte",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Formulario
                var paraText by remember { mutableStateOf("") }
                var tituloText by remember { mutableStateOf("") }
                var asuntoText by remember { mutableStateOf("") }

                TextField(
                    value = paraText,
                    onValueChange = { paraText = it },
                    label = { Text("Para") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        unfocusedIndicatorColor = Color.Transparent, // Línea inferior oculta cuando no está enfocado
                        focusedIndicatorColor = Color.Transparent // Línea inferior oculta cuando está enfocado
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                TextField(
                    value = tituloText,
                    onValueChange = { tituloText = it },
                    label = { Text("Título") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                TextField(
                    value = asuntoText,
                    onValueChange = { asuntoText = it },
                    label = { Text("Asunto") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp), // Cambiado a 16.dp para alinear mejor los botones
                    maxLines = 4
                )

                // Botones de Enviar y Cancelar
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                            startActivity(Intent(this@Redactar, Notificaciones::class.java)) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF009e00) // Color verde
                        ),
                        modifier = Modifier.weight(1f) // Se asegura de que ocupe el mismo espacio
                    ) {
                        Text(
                            text = "Enviar Reporte",
                            color = Color.Unspecified, // Sin color específico para el texto
                            fontSize = 12.sp, // Ajusta el tamaño de la letra aquí
                            modifier = Modifier.fillMaxWidth(), // Asegúrate de que el texto ocupe todo el ancho
                            textAlign = TextAlign.Center // Centrar el texto
                        )
                    }

                    Button(
                        onClick = { // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                            startActivity(Intent(this@Redactar, Notificaciones::class.java)) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.weight(1f) // Se asegura de que ocupe el mismo espacio
                    ) {
                        Text(
                            text = "Cancelar",
                            color = Color.Unspecified, // Sin color específico para el texto
                            fontSize = 12.sp, // Ajusta el tamaño de la letra aquí
                            modifier = Modifier.fillMaxWidth(), // Asegúrate de que el texto ocupe todo el ancho
                            textAlign = TextAlign.Center // Centrar el texto
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
package com.example.superadmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {

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
            ButtonGrid()
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
                Text(
                    "Etapa\nProductiva",
                    fontSize = 13.sp,
                    color = Color(0xFF009E00),
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
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
                        Text("Ver perfil")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                        expanded = false
                    }) {
                        Text("Inicio")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, Configuracion ::class.java))
                        expanded = false
                    }) {
                        Text("Configuración")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, "Permisos"::class.java))
                        expanded = false
                    }) {
                        Text("Permisos")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, AdministradoresActivity::class.java))
                        expanded = false
                    }) {
                        Text("Administradores")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, InstructorActivity::class.java))
                        expanded = false
                    }) {
                        Text("Instructores")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, ApprenticeActivity::class.java))
                        expanded = false
                    }) {
                        Text("Aprendices")
                    }
                    DropdownMenuItem(onClick = {
                        context.startActivity(Intent(context, GraphicActivity::class.java))
                        expanded = false
                    }) {
                        Text("Gráficas")
                    }
                    DropdownMenuItem(onClick = {
                        // Implementar lógica de cierre de sesión
                        expanded = false
                    }) {
                        Text("Cerrar sesión")
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
    fun ButtonGrid() {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ActionButton("Administradores", R.drawable.administrador) {
                    Log.d("MainActivity", "Administradores button clicked")
                    startActivity(Intent(this@MainActivity, AdministradoresActivity::class.java))
                }

                ActionButton("Instructores", R.drawable.instructor_icono) {
                    Log.d("MainActivity", "Instructores button clicked")
                    startActivity(Intent(this@MainActivity, InstructorActivity::class.java))
                }
                ActionButton("Aprendices", R.drawable.aprendiz_icono) {
                    Log.d("MainActivity", "Aprendices button clicked")
                    startActivity(Intent(this@MainActivity, ApprenticeActivity::class.java))
                }
                ActionButton("Gráficas", R.drawable.graficas) {
                    Log.d("MainActivity", "Gráficas button clicked")
                    startActivity(Intent(this@MainActivity, GraphicActivity::class.java))
                }
            }
        }
    }

    @Composable
    fun ActionButton(text: String, iconRes: Int, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp) // Cambia el tamaño según sea necesario
                .padding(8.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp), clip = false) // Sombra difuminada
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxSize(), // Ocupa todo el espacio del Box
                shape = RoundedCornerShape(8.dp),
                colors = androidx.compose.material.ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = text,
                        modifier = Modifier.size(if (iconRes == R.drawable.administrador) 100.dp else 80.dp)
                    )
                    Text(
                        text = text,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = if (iconRes == R.drawable.administrador) {
                            Modifier.offset(y = (-4).dp) // Subir texto solo en botón Administrador
                        } else {
                            Modifier.offset(y = 8.dp) // Sin desplazamiento en otros botones
                        }
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Perfil_Aprendiz : ComponentActivity() {
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
            MainContent()  // Include this to render the profile content
        }
    }

    @Composable
    fun HeaderSection() {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_sena),
                contentDescription = "SENA Logo",
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_etapaproductiva),
                        contentDescription = "Etapa Productiva Logo",
                        modifier = Modifier.size(40.dp)
                    )
                    Column {
                        Text(
                            "Etapa",
                            fontSize = 12.sp,
                            color = Color(0xFF009E00),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Productiva",
                            fontSize = 12.sp,
                            color = Color(0xFF009E00),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text("Centro de Comercio y Servicios", fontSize = 14.sp, color = Color(0xFF009E00))
            }

            Spacer(modifier = Modifier.weight(1f))

            // Imagen de mujer convertida en botón
            Image(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                        startActivity(Intent(this@Perfil_Aprendiz, "Perfil_instructor"::class.java))
                    }
            )
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
                painter = painterResource(id = R.drawable.notificaciones_icon),
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                        startActivity(Intent(this@Perfil_Aprendiz, "Notificaciones"::class.java)) },
                colorFilter = ColorFilter.tint(Color.White)

            )
        }
    }
    @Composable
    fun SearchBar() {
        // Crear un estado mutable para el texto de búsqueda
        var searchText by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { finish() }) {
                Image(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = "Flecha",
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(2.dp))

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
            // Sección de información del perfil
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.aprendiz),
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
                UserProfileField(label = "Nivel de Formación", value = "Tecnologo")
                UserProfileField(label = "Nombre del Programa", value = "Adso")
            }

            // Sección de botones Bitácora y Visita en la misma fila
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly // Espacio entre los botones
            ) {

            }

            // Dropdown de estado
            StatusDropdown()
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

    @Composable
    fun StatusDropdown() {
        var selectedOption by remember { mutableStateOf("Selecciona Opción") }
        val options = listOf("ACTIVO", "NOVEDAD", "FINALIZADA")
        var expanded by remember { mutableStateOf(false) }

        Surface(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                Text(
                    text = selectedOption,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(12.dp)
                        .fillMaxWidth()
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOption = option
                                expanded = false
                            }
                        ) {
                            Text(text = option)
                        }
                    }
                }
            }
        }
    }

    private fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {
        TODO("Not yet implemented")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainContent()
    }
}

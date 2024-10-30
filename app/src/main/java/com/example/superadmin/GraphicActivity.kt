package com.example.superadmin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.superadmin.ui.theme.SuperAdminTheme
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineDataSet

class GraphicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperAdminTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GraphicScreen()
                }
            }
        }
    }

    @Composable
    fun GraphicScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(ComposeColor.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            NotificationBar()
            FilterSection()
            Spacer(modifier = Modifier.height(16.dp))
            GraphicContent() // Contenido gráfico
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
                    color = ComposeColor(0xFF009E00),
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .offset(x = (-5).dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 14.sp,
                    color = ComposeColor(0xFF009E00),
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
                        context.startActivity(Intent(context, Configuracion::class.java))
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
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(ComposeColor(0xFF009E00)),
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
                colorFilter = ColorFilter.tint(ComposeColor.White)
            )
        }
    }

    @Composable
    fun FilterSection() {
        var selectedContract by remember { mutableStateOf("Todos") }
        var selectedYear by remember { mutableStateOf("2024") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(ComposeColor(0xFFF0F0F0), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text("Filtros", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = ComposeColor(0xFF009E00))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DropdownMenuContractType(selectedContract) { selectedContract = it }
                DropdownMenuYear(selectedYear) { selectedYear = it }
            }
        }
    }

    @Composable
    fun DropdownMenuContractType(selectedContract: String, onSelected: (String) -> Unit) {
        val options = listOf("Todos", "Pasantía", "Vínculo Laboral", "Contrato de Aprendizaje", "Unidad Productiva", "Proyecto Empresarial")
        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.padding(end = 8.dp)) {
            Text(
                text = selectedContract,
                modifier = Modifier
                    .clickable { expanded = true }
                    .background(ComposeColor.White, RoundedCornerShape(4.dp))
                    .padding(12.dp)
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        onSelected(option)
                        expanded = false
                    }) {
                        Text(option)
                    }
                }
            }
        }
    }

    @Composable
    fun DropdownMenuYear(selectedYear: String, onSelected: (String) -> Unit) {
        val years = (2020..2024).map { it.toString() }
        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = selectedYear,
                modifier = Modifier
                    .clickable { expanded = true }
                    .background(ComposeColor.White, RoundedCornerShape(4.dp))
                    .padding(12.dp)
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                years.forEach { year ->
                    DropdownMenuItem(onClick = {
                        onSelected(year)
                        expanded = false
                    }) {
                        Text(year)
                    }
                }
            }
        }
    }

    @Composable
    fun GraphicContent() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Gráfica Circular
            Text("Gráfica Circular de Participación", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = ComposeColor(0xFF009E00))
            AndroidView(
                factory = { context ->
                    PieChart(context).apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500)
                    }
                },
                update = { pieChart ->
                    val entries = listOf(
                        PieEntry(40f, "Participantes A"),
                        PieEntry(30f, "Participantes B"),
                        PieEntry(20f, "Participantes C"),
                        PieEntry(10f, "Participantes D")
                    )
                    val dataSet = PieDataSet(entries, "Participación").apply {
                        setColors(intArrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW), 255)
                    }
                    pieChart.data = PieData(dataSet)
                    pieChart.invalidate()
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Gráfica de Barras
            Text("Gráfica de Barras de Participación", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = ComposeColor(0xFF009E00))
            AndroidView(
                factory = { context ->
                    BarChart(context).apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500)
                    }
                },
                update = { barChart ->
                    val entries = listOf(
                        BarEntry(0f, 10f),
                        BarEntry(1f, 20f),
                        BarEntry(2f, 30f),
                        BarEntry(3f, 40f)
                    )
                    val dataSet = BarDataSet(entries, "Participación").apply {
                        colors = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW)
                    }
                    barChart.data = BarData(dataSet)
                    barChart.invalidate()
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Gráfica de Líneas
            Text("Gráfica de Líneas de Participación", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = ComposeColor(0xFF009E00))
            AndroidView(
                factory = { context ->
                    LineChart(context).apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500)
                    }
                },
                update = { lineChart ->
                    val entries = listOf(
                        Entry(0f, 15f),
                        Entry(1f, 25f),
                        Entry(2f, 20f),
                        Entry(3f, 35f)
                    )
                    val dataSet = LineDataSet(entries, "Participación").apply {
                        color = Color.BLUE
                        valueTextColor = Color.BLACK
                    }
                    lineChart.data = LineData(dataSet)
                    lineChart.invalidate()
                }
            )
        }
    }
}

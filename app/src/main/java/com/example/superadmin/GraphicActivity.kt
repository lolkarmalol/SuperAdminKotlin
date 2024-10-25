package com.example.superadmin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

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
                .background(ComposeColor.White)
        ) {
            HeaderSection()
            NotificationBar()
            FilterSection()
            GraphicContent()
        }
    }

    @Composable
    fun HeaderSection() {
        val context = LocalContext.current // Obtener el contexto local

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top // Cambiar a Alignment.Top
        ) {
            // Logo SENA
            Image(
                painter = painterResource(id = R.drawable.logo_sena),
                contentDescription = "Logo SENA",
                modifier = Modifier
                    .size(70.dp)
                    .clickable { context.startActivity(Intent(context, MainActivity::class.java)) }
            )
            Spacer(modifier = Modifier.width(10.dp))

            // Logo Etapa Productiva
            Image(
                painter = painterResource(id = R.drawable.logo_etapaproductiva),
                contentDescription = "Logo Etapa Productiva",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { context.startActivity(Intent(context, MainActivity::class.java)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Textos
            Column(
                modifier = Modifier.clickable { context.startActivity(Intent(context, MainActivity::class.java)) }
            ) {
                Text(
                    "Etapa\nProductiva",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ComposeColor(0xFF009E00),
                    modifier = Modifier.padding(top = 6.dp)
                )
                Text(
                    "Centro de Comercio y Servicios",
                    fontSize = 12.sp,
                    color = ComposeColor(0xFF009E00),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Para empujar el icono del usuario a la derecha

            // Icono de usuario
            Image(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(45.dp)
                    .clickable { /* Add action for user icon if needed */ }
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
                        // Al hacer clic en el icono de notificaciones, iniciar Notificaciones
                        context.startActivity(Intent(context, Notificaciones::class.java))
                    },
                colorFilter = ColorFilter.tint(ComposeColor.White) // Cambia el color a blanco
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
            Text(
                text = "Año Actual",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // Distribución uniforme
            ) {
                GraphicStats("Pasantía", 150)
                GraphicStats("Vínculo Laboral", 250)
                GraphicStats("Contrato de Aprendizaje", 110)
                GraphicStats("Unidad Productiva", 190)
                GraphicStats("Proyecto Empresarial", 100)
            }
            Spacer(modifier = Modifier.height(8.dp))
            GraphicChartSection()
        }
    }

    @Composable
    fun GraphicChartSection() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AndroidView(factory = { context ->
                PieChart(context).apply {
                    val entries = listOf(
                        PieEntry(150f, "Pasantía"),
                        PieEntry(250f, "Vínculo"),
                        PieEntry(110f, "Aprendizaje"),
                        PieEntry(190f, "Unidad Prod."),
                        PieEntry(100f, "Proyecto Emp.")
                    )

                    val dataSet = PieDataSet(entries, "Contratos").apply {
                        colors = listOf(
                            Color.parseColor("#42A5F5"),
                            Color.parseColor("#66BB6A"),
                            Color.parseColor("#FFA726"),
                            Color.parseColor("#EF5350"),
                            Color.parseColor("#AB47BC")
                        )
                        valueTextSize = 14f
                        valueTextColor = Color.BLACK
                    }

                    data = PieData(dataSet)
                    legend.apply {
                        isEnabled = true
                        textSize = 14f
                        horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                        verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                        orientation = Legend.LegendOrientation.HORIZONTAL
                    }

                    description.isEnabled = false
                    setUsePercentValues(true)
                    animateY(1400)
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .height(300.dp))
        }
    }

    @Composable
    fun GraphicStats(title: String, value: Int) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ComposeColor(0xFF009E00))
            Text(text = value.toString(), fontSize = 24.sp, color = ComposeColor(0xFF009E00))
        }
    }
}

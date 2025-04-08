package com.armas.carlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.armas.carlist.ui.theme.CarListTheme
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color



data class Auto(
    val nombre: String,
    val modelo: String,
    val anio: Int,
    val descripcion: String,
    val imagenRes: Int
)

val autos = listOf(
    Auto("Toyota Supra", "MK4", 1998, "Auto icónico japonés con gran potencial de modificación.", R.drawable.supra),
    Auto("Mazda RX-7", "FD", 1999, "Conocido por su motor rotativo y diseño elegante.", R.drawable.rx7),
    Auto("Nissan Skyline", "GT-R R34", 2002, "Auto legendario con alto rendimiento en carreras callejeras y circuitos aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.", R.drawable.r34),
    Auto("Ford Mustang", "GT", 2020, "Un clásico americano con potencia bruta y estilo agresivo.", R.drawable.mustang),
    Auto("Chevrolet Camaro", "ZL1", 2021, "Muscle car moderno con alto desempeño y tecnología.", R.drawable.camaro),
    Auto("Tesla Model S", "Plaid", 2022, "Sedán eléctrico con aceleración impresionante y gran autonomía.", R.drawable.tesla),
            Auto("Toyota Supra", "MK4", 1998, "Auto icónico japonés con gran potencial de modificación.", R.drawable.supra),
Auto("Mazda RX-7", "FD", 1999, "Conocido por su motor rotativo y diseño elegante.", R.drawable.rx7),
Auto("Nissan Skyline", "GT-R R34", 2002, "Auto legendario con alto rendimiento en carreras callejeras y circuitos.", R.drawable.r34),
Auto("Ford Mustang", "GT", 2020, "Un clásico americano con potencia bruta y estilo agresivo.", R.drawable.mustang),
Auto("Chevrolet Camaro", "ZL1", 2021, "Muscle car moderno con alto desempeño y tecnología.", R.drawable.camaro),
Auto("Tesla Model S", "Plaid", 2022, "Sedán eléctrico con aceleración impresionante y gran autonomía.", R.drawable.tesla)
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarListTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Usamos un LazyColumn para permitir el desplazamiento
                    ListaDeAutos(autos)
                }
            }
        }
    }
}

/*@Composable
fun ListaDeAutos(listaAutos: List<Auto>) {
    Column(modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
        // Título
        Text(
            text = "Lista de Autos",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista desplazable
        LazyColumn {
            items(listaAutos) { auto ->
                TarjetaAuto(auto)
            }
        }
    }
}*/



/*@Composable
fun ListaDeAutos(listaAutos: List<Auto>) {
    // Usamos LazyVerticalGrid para mostrar los elementos en grilla
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Definir número de columnas
        modifier = Modifier.padding(8.dp)
    ) {
        items(listaAutos) { auto ->
            TarjetaAuto(auto)
        }
    }
}*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListaDeAutos(listaAutos: List<Auto>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxSize()
    ) {
        // Sticky Header
        stickyHeader {
            Surface(
                color = Color.Transparent
            ) {
                Text(
                    text = "Lista de Autos",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold, // Negrita
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        // Items de la lista
        items(listaAutos) { auto ->
            TarjetaAuto(auto)
        }
    }
}


/*@Composable
fun TarjetaAuto(auto: Auto) {
    var expandido by remember { mutableStateOf(false) }

    // La tarjeta se hace clickable
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { expandido = !expandido }, // Cambiar el estado al hacer clic
        elevation = CardDefaults.cardElevation(8.dp), // Mayor elevación para profundidad
        shape = MaterialTheme.shapes.medium // Bordes redondeados en la tarjeta
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Imagen con bordes redondeados y con un contorno
            Image(
                painter = painterResource(id = auto.imagenRes),
                contentDescription = auto.nombre,
                modifier = Modifier
                    .width(120.dp) // Usar un tamaño fijo para la imagen
                    .height(120.dp) // Para que no se deforme
                    .clip(MaterialTheme.shapes.medium) // Bordes redondeados
                    .border(2.dp, MaterialTheme.colorScheme.onSurfaceVariant, MaterialTheme.shapes.medium), // Contorno
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Columna con los datos del auto
            Column(
                modifier = Modifier
                    .weight(1f) // Esto permite que la columna ocupe el espacio restante
                    .padding(end = 8.dp) // Añadir algo de espacio en el lado derecho
            ) {
                // Títulos de los autos con mayor peso visual
                Text(
                    text = "${auto.nombre}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface, // Color más visible
                    modifier = Modifier.padding(bottom = 4.dp) // Espacio debajo del título
                )
                Text(
                    text = "${auto.modelo} - ${auto.anio}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant, // Color menos intenso para el año
                    modifier = Modifier.padding(bottom = 8.dp) // Espacio debajo del año
                )

                // Descripción del auto, colapsable y expandible
                if (expandido) {
                    Text(
                        text = auto.descripcion,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                } else {
                    Text(
                        text = auto.descripcion,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}*/

@Composable
fun TarjetaAuto(auto: Auto) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { expandido = !expandido },
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen en la parte superior
            Image(
                painter = painterResource(id = auto.imagenRes),
                contentDescription = "${auto.nombre} ${auto.modelo} del año ${auto.anio}",
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.onSurfaceVariant,
                        MaterialTheme.shapes.medium
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Título
            Text(
                text = auto.nombre,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Modelo y Año
            Text(
                text = "${auto.modelo} - ${auto.anio}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Descripción expandible
            if (expandido) {
                Text(
                    text = auto.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                Text(
                    text = auto.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VistaPrevia() {
    CarListTheme {
        // Usamos LazyColumn para permitir el desplazamiento también en la vista previa
        ListaDeAutos(autos)
    }
}






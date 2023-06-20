package edu.itsco.taxi.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import edu.itsco.taxi.Routes
import edu.itsco.taxi.ui.theme.TaxiTheme
import edu.itsco.taxi.view.MainView

val cosamaloapan = LatLng(18.36443, -95.79210)
val adoTAXIS = LatLng(18.27881, -96.00153)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    val defaultCameraPosition =CameraPosition.fromLatLngZoom(cosamaloapan,18f)
    val cameraPositionState = rememberCameraPositionState{
        position = defaultCameraPosition
    }
    var isMapLoaded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMapView(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            }
        )
        if (!isMapLoaded){
            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ){
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )
            }
        }
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var nombre by remember {
        mutableStateOf("")
    }
    var calle by remember {
        mutableStateOf("")
    }
    var referencias by remember {
        mutableStateOf("")
    }
    var acepto by remember {
        mutableStateOf(false)
    }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Enviar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text(text = "Cancelar")
                    }
                },
                title = { Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("FORMULARIO") }
                },
                text = {
                    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Ingrese sus datos")
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),

                            placeholder = { Text("Nombre") }
                        )
                        OutlinedTextField(
                            value = calle,
                            onValueChange = { calle = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            placeholder = { Text("Calle") }
                        )
                        OutlinedTextField(
                            value = referencias,
                            onValueChange = { referencias = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            placeholder = { Text("Referencias") }
                        )
                        Row() {

                        }
                    }

                }
            )
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            containerColor = Color.Black,
            contentColor = Color.White
        ) {
            Icon(Icons.Filled.Call, "")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ){
        FloatingActionButton(
            onClick = {
                navController.navigate(Routes.Intro.route)
            },
            containerColor = Color.Black,
            contentColor = Color.White
        ) {
            Icon(Icons.Filled.Close, "")
        }
    }
}


@Composable
fun GoogleMapView(
    modifier: Modifier=Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () ->Unit = {},
    content: @Composable () -> Unit = {}

){
    val adoTaxisState = rememberMarkerState(position = adoTAXIS)
    val cosamaloapanState = rememberMarkerState(position = cosamaloapan)

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapLoaded = onMapLoaded
    ){
        Marker(
            state = cosamaloapanState,
            title = "Base de Taxis"
        )

        content()
    }



}
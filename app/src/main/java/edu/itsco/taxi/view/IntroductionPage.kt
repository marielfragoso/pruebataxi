package edu.itsco.taxi.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.itsco.taxi.R
import edu.itsco.taxi.Routes
import edu.itsco.taxi.ui.theme.Purple40
import edu.itsco.taxi.ui.theme.Purple80
import edu.itsco.taxi.ui.theme.Teal200

@Composable
fun IntroductionPage(navController: NavController){
    Box{
        Surface (
            color = Purple80,
            modifier = Modifier.fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            ){
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Purple80),
                    modifier = Modifier.fillMaxWidth(.8f),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    onClick = { navController.navigate(Routes.Login.route) },
                ) {
                    Text(text = "Iniciar sesion", color = Color.White)
                }
                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Purple80),
                    modifier = Modifier.fillMaxWidth(.8f),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    onClick = { navController.navigate(Routes.SignUp.route) },
                ) {
                    Text(text = "Registrarse", color = Color.White)
                }
            }
        }
        
        Surface (
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.75f),
            shape = RoundedCornerShape(60.dp).copy(
                topEnd = ZeroCornerSize,
                topStart = ZeroCornerSize
            )
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(.25f))
                Text(text = "BIENVENIDO", style = MaterialTheme.typography.headlineLarge, color = Purple40,
                modifier = Modifier.padding(top = 20.dp)
                )
                Image(painter = painterResource(id = R.drawable.intro), contentDescription = "welcome",
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop)
            }
            
        }
    }
    
}

@Preview
@Composable
fun Introduction(){
    val nav = rememberNavController()
    IntroductionPage(navController = nav)
}
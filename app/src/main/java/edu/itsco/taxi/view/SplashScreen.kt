package edu.itsco.taxi.view

import android.media.Image
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import edu.itsco.taxi.R
import edu.itsco.taxi.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    val scale = remember{
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            ),
        )
        delay(2000L)
        //navController.navigate(Screens.LoginScreen.name)
        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(Routes.Intro.route)
        }else{
            navController.navigate(Routes.Intro.route){
                popUpTo(Routes.Splash.route){
                    inclusive = true
                }
            }
        }
    }

    val color = MaterialTheme.colorScheme.primary
    Surface(modifier = Modifier
        .padding(10.dp)
        .size(800.dp)
        .scale(scale.value),
        //shape = CircleShape,
        //color = Color.White,
        //border = BorderStroke(width = 1.dp, color = color)
    ) {
        Column(modifier = Modifier
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(280.dp),painter = painterResource(id = R.drawable.splash) ,
                contentDescription = "Icono")

        }
    }
}
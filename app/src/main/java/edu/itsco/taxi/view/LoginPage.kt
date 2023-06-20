package edu.itsco.taxi.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.itsco.taxi.R
import edu.itsco.taxi.Routes
import edu.itsco.taxi.ui.theme.Purple80
import kotlinx.coroutines.launch


@Composable
fun LoginPage(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Purple80,
                        Purple80.copy(alpha = .5f),
                        Purple80.copy(alpha = .5f),
                        Purple80
                    )

                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(.1f))
        Image(
            painterResource(id = R.drawable.login),
            contentDescription = "login",
            modifier = Modifier.fillMaxHeight(.3f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        InputLayout(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputLayout(navController: NavController,
                viewModel: SignInViewModel = hiltViewModel()) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxWidth(.8f)
            .background(
                color = Color.White.copy(alpha = .4f),
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Correo")
            },
            label = { Text(text = "Correo electronico") },
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth(.85f)
                .padding(top = 25.dp),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Purple80,
                containerColor = Color.White
            )
        )


        OutlinedTextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Create, contentDescription = "Contraseña")
            },
            label = { Text(text = "Contraseña") },
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth(.85f)
                .padding(top = 25.dp),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Purple80,
                containerColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(.85f)
                .padding(20.dp),
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
                    if (viewModel.logueado) {
                        navController.navigate(Routes.Home.route)
                    }
                }
            },
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple80)
        ) {
            Text(text = "Iniciar sesión", color = Color.White)
        }
        if (state.value?.isLoading == true) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(20.dp))

    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
            }
        }
    }
}






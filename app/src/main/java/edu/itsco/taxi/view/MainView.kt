package edu.itsco.taxi.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.itsco.taxi.Routes

@Composable
fun MainView(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Splash.route){
        composable(route = Routes.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Routes.Intro.route){
            IntroductionPage(navController = navController)
        }
        composable(Routes.Login.route){
            LoginPage(navController = navController)
        }
        composable(Routes.SignUp.route){
            SinUpPage(navController = navController)
        }
        composable(route = Routes.Home.route) {
            HomeScreen(navController = navController)
        }


    }
}
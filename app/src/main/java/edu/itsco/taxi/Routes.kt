package edu.itsco.taxi

sealed class Routes(val route : String) {
    object Intro: Routes("Intro")
    object Login: Routes("Login")
    object SignUp: Routes("SignUp")
    object Home : Routes("Home")
    object Splash : Routes("Screen")
}
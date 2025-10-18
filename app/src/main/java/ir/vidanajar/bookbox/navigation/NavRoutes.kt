package ir.vidanajar.bookbox.navigation

sealed class NavRoutes(val route: String) {

    object Splash : NavRoutes("splash")
    object Login : NavRoutes("login")
    object SignUp : NavRoutes("signUp")
    object Home : NavRoutes("home")

}
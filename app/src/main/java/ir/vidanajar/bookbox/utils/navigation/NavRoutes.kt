package ir.vidanajar.bookbox.utils.navigation

sealed class NavRoutes(val route: String) {

    object Splash : NavRoutes("splash")
    object Login : NavRoutes("login")
    object SignUp : NavRoutes("signUp")

    //Main route
    object Main : NavRoutes("main")

    // bottom navigation bar
    object Home : NavRoutes("home")
    object Search : NavRoutes("search")
    object Shelves : NavRoutes("shelves")
    object Profile : NavRoutes("profile")

}
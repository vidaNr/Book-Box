package ir.vidanajar.bookbox.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.vidanajar.bookbox.ui.HomeScreen
import ir.vidanajar.bookbox.ui.login.LoginScreen
import ir.vidanajar.bookbox.ui.login.LoginViewModel

sealed class Screens(val route: String) {

    object Login : Screens("login")
    //    object SignUp : Screens("signUp")
    object Home : Screens("home")

}

@Composable
fun AppNavGraph(modifier: Modifier? = Modifier, navController: NavHostController) {

//    val signupViewModel: SignUpViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()


    NavHost(navController = navController, startDestination = Screens.Login.route) {
        composable(Screens.Login.route) {

            LoginScreen(
                modifier = Modifier,
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate(Screens.Home.route) },
                onSignUpClick = {}
            )
        }
//
//        composable(Screens.SignUp.route) {
//
//
//        }

        composable(Screens.Home.route) {
            HomeScreen()
        }
    }


}
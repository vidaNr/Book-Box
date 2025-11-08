package ir.vidanajar.bookbox.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.vidanajar.bookbox.data.local.TokenDataStore
import ir.vidanajar.bookbox.ui.SplashScreen
import ir.vidanajar.bookbox.ui.home.HomeScreen
import ir.vidanajar.bookbox.ui.home.HomeViewModel
import ir.vidanajar.bookbox.ui.login.LoginScreen
import ir.vidanajar.bookbox.ui.login.LoginViewModel
import ir.vidanajar.bookbox.ui.signup.SignUpScreen
import ir.vidanajar.bookbox.ui.signup.SignUpViewModel


@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    tokenDataStore: TokenDataStore
) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        composable(NavRoutes.Splash.route) {
            SplashScreen(navController, tokenDataStore)
        }
        composable(NavRoutes.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                modifier,
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Splash.route) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(NavRoutes.SignUp.route)
                },
                navController = navController,
                tokenDataStore = tokenDataStore
            )
        }
        composable(NavRoutes.SignUp.route) {
            val signupViewModel: SignUpViewModel = hiltViewModel()
            SignUpScreen(
                modifier,
                viewModel = signupViewModel,
                onSignUpSuccess = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Splash.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(NavRoutes.Login.route)
                }
            )
        }

        composable(NavRoutes.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                modifier = modifier,
                viewModel = homeViewModel,
                onBookClicked = { /*TODO*/  }
            )
        }
    }

}
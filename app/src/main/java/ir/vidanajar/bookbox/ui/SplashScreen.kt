package ir.vidanajar.bookbox.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ir.vidanajar.bookbox.data.local.TokenDataStore
import ir.vidanajar.bookbox.utils.navigation.NavRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    tokenStore: TokenDataStore
) {
    val tokenState by tokenStore.token.collectAsState(initial = "")

    LaunchedEffect(tokenState) {
        delay(1500)

        when {
            tokenState == null -> {
                navController.navigate(NavRoutes.Login.route) {
                    popUpTo(NavRoutes.Splash.route) { inclusive = true }
                }
            }
            tokenState!!.isNotEmpty() -> {
                navController.navigate(NavRoutes.Home.route) {
                    popUpTo(NavRoutes.Splash.route) { inclusive = true }
                }
            }
            else -> {
                navController.navigate(NavRoutes.Login.route) {
                    popUpTo(NavRoutes.Splash.route) { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

package ir.vidanajar.bookbox.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ir.vidanajar.bookbox.data.local.TokenDataStore
import ir.vidanajar.bookbox.utils.navigation.NavRoutes


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    tokenDataStore: TokenDataStore,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.loginState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        when (state) {
            is LoginState.Success -> {
                val token = (state as LoginState.Success).token
                tokenDataStore.saveToken(token)
                navController.navigate(NavRoutes.Main.route) {
                    popUpTo(NavRoutes.Login.route) { inclusive = true }
                }
            }
            is LoginState.Error -> {
                Log.d("LoginScreen", "Login failed: ${(state as LoginState.Error).message}")
            }
            else -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Spacer(modifier = modifier.height(100.dp))
            Text(
                "Login",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )
            Spacer(modifier = modifier.height(80.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                singleLine = true,
                label = { Text("Email") },
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = modifier.height(24.dp))
            Button(
                onClick = { viewModel.login(email, password) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    "Confirm",
                    style = TextStyle(fontSize = 18.sp),
                    modifier = modifier.padding(8.dp)
                )
            }
            Spacer(modifier = modifier.height(8.dp))
            TextButton(onClick = { onSignUpClick() }) {
                Text("Don’t have an account? Sign Up")
            }
        }
    }
}

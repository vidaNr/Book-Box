package ir.vidanajar.bookbox.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.vidanajar.bookbox.ui.home.HomeScreen
import ir.vidanajar.bookbox.ui.home.HomeViewModel
import ir.vidanajar.bookbox.utils.navigation.NavRoutes

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val innerNavController = rememberNavController()
    val bottomItem = listOf<NavRoutes>(
        NavRoutes.Home,
        NavRoutes.Search,
        NavRoutes.Shelves,
        NavRoutes.Profile,
    )

    Scaffold(
        bottomBar = {
            val currentRoute =
                innerNavController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute in bottomItem.map { it.route }) {

                NavigationBar {
                    bottomItem.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                innerNavController.navigate(item.route) {
                                    popUpTo(innerNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = when (item) {
                                        NavRoutes.Home -> Icons.Default.Home
                                        NavRoutes.Shelves -> Icons.Default.Menu
                                        NavRoutes.Search -> Icons.Default.Search
                                        NavRoutes.Profile -> Icons.Default.Person
                                        else -> Icons.Default.Home
                                    },
                                    contentDescription = item.route
                                )
                            },
                            label = { Text(item.route.replaceFirstChar { it.uppercase() }) }
                        )
                    }
                }
            }
        }) { paddingValues ->
        NavHost(
            navController = innerNavController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoutes.Home.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    modifier = modifier,
                    viewModel = homeViewModel,
                    onBookClicked = {/* TODO */ }
                )
            }
            composable(NavRoutes.Search.route) { /*searchScreen*/ }
            composable(NavRoutes.Shelves.route) { /*Shelves*/ }
            composable(NavRoutes.Profile.route) { /*Profile*/ }
        }
    }
}
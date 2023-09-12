package navcontroller

import LoginScreen
import Screen
import androidx.compose.runtime.Composable
import screens.HomeScreen
import screens.RegisterScreen

@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {
        composable(Screen.HomeScreen.name) {
            HomeScreen(navController)
        }

        composable(Screen.LoginScreen.name) {
            LoginScreen(navController)
        }

        composable(Screen.RegisterScreen.name) {
            RegisterScreen(navController)
        }


    }.build()
}
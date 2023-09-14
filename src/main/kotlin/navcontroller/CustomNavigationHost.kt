package navcontroller

import LoginScreen
import Screen
import androidx.compose.runtime.Composable
import screens.RegisterScreen
import screens.homeScreen

@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {
        composable(Screen.HomeScreen.name) {
            homeScreen(navController)
        }

        composable(Screen.LoginScreen.name) {
            LoginScreen(navController)
        }

        composable(Screen.RegisterScreen.name) {
            RegisterScreen(navController)
        }


    }.build()
}
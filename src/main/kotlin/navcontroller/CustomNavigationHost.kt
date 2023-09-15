package navcontroller

import Screen
import androidx.compose.runtime.Composable
import loginScreen
import screens.homeScreen
import screens.registerScreen

/**
 * This @Composable function sets up the custom navigation host which defines the different screens
 * and their respective navigation logic using the [NavigationHost] and [NavController].
 *
 * @param navController The [NavController] instance to control navigation between different screens.
 */
@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    // Initializing the NavigationHost with the defined navigation graph
    NavigationHost(navController) {

        // Defining a composable function for the home screen and specifying its route using Screen.HomeScreen.name
        // The home screen UI is defined in the homeScreen composable function which takes navController as a parameter
        composable(Screen.HomeScreen.name) {
            homeScreen(navController)
        }

        // Defining a composable function for the login screen with its route and UI function
        composable(Screen.LoginScreen.name) {
            loginScreen(navController)
        }

        // Defining a composable function for the register screen with its route and UI function
        composable(Screen.RegisterScreen.name) {
            registerScreen(navController)
        }

    }.build()  // Building the navigation graph
}

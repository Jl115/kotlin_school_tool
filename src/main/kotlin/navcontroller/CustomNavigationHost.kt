package navcontroller

import LoginScreen
import Screen
import androidx.compose.runtime.Composable
import screens.HomeScreen
import screens.ProfileScreen
import screens.SettingScreen
import screens.customComponents.navigationHeaderBar

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

        composable(Screen.SettingsScreen.name) {
            SettingScreen(navController)
        }

        composable(Screen.ProfileScreens.name) {
            ProfileScreen(navController)
        }



    }.build()
}
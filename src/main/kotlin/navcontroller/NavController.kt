package navcontroller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * NavController class facilitates the navigation between different composables/screens in a Jetpack Compose application.
 *
 * @property startDestination The route identifier for the start destination or the initial screen of the navigation graph.
 * @property backStackScreens A mutable set to hold the routes of the screens in the back stack.
 */
class NavController(
    private val startDestination: String,
    private var backStackScreens: MutableSet<String> = mutableSetOf()
) {
    // Holds the state of the current screen with the initial value set to the start destination.
    var currentScreen: MutableState<String> = mutableStateOf(startDestination)

    /**
     * Navigates to the specified route while properly managing the back stack.
     *
     * @param route The route identifier for the screen to navigate to.
     */
    fun navigate(route: String) {
        // Ensures navigation only occurs for a different route than the current one
        if (route != currentScreen.value) {
            // Handles removing the current screen from the back stack if necessary
            if (backStackScreens.contains(currentScreen.value) && currentScreen.value != startDestination) {
                backStackScreens.remove(currentScreen.value)
            }

            // Handles resetting the back stack if navigating to the start destination
            if (route == startDestination) {
                backStackScreens = mutableSetOf()
            } else {
                // Adds the current screen to the back stack
                backStackScreens.add(currentScreen.value)
            }

            // Updates the current screen to the new route
            currentScreen.value = route
        }
    }

    /**
     * Navigates back to the previous screen in the back stack, if any.
     */
    fun navigateBack() {
        // Checks if there are any screens in the back stack to navigate back to
        if (backStackScreens.isNotEmpty()) {
            // Sets the current screen to the last screen in the back stack
            currentScreen.value = backStackScreens.last()
            // Removes the last screen from the back stack
            backStackScreens.remove(currentScreen.value)
        }
    }
}

/**
 * A @Composable function to create and remember a NavController with a specified start destination and initial back stack screens.
 *
 * @param startDestination The route identifier for the start destination or the initial screen of the navigation graph.
 * @param backStackScreens A mutable set to hold the routes of the initial screens in the back stack.
 * @return A mutable state holding the NavController instance.
 */
@Composable
fun rememberNavController(
    startDestination: String,
    backStackScreens: MutableSet<String> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    // Creates and remembers a NavController with the specified parameters
    mutableStateOf(NavController(startDestination, backStackScreens))
}

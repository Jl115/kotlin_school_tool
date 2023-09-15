package navcontroller

import androidx.compose.runtime.Composable

/**
 * This class acts as a host for navigation graph where different screens of the app are defined.
 *
 * @property navController The NavController instance that will be used for navigation between different composables/screens.
 * @property contents A lambda function which uses NavigationGraphBuilder to define different screens and their respective navigation logic.
 */
class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {

    /**
     * This @Composable function builds the navigation graph using the contents lambda function.
     */
    @Composable
    fun build() {
        NavigationGraphBuilder().renderContents()
    }

    /**
     * Inner class which acts as a builder to help define the navigation graph.
     *
     * @property navController The NavController instance inherited from the NavigationHost to be used in the navigation graph.
     */
    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController
    ) {
        /**
         * This @Composable function uses the contents lambda function to build different screens in the navigation graph.
         */
        @Composable
        fun renderContents() {
            this@NavigationHost.contents(this)
        }
    }
}

/**
 * Extension function on NavigationGraphBuilder class to define a composable function for a specific route in the navigation graph.
 *
 * @param route The route identifier for the screen.
 * @param content The @Composable function that describes the UI of the screen.
 */
@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: String,
    content: @Composable () -> Unit
) {
    // Checking if the current screen's route matches the given route and if so, the content lambda function is invoked to build the screen.
    if (navController.currentScreen.value == route) {
        content()
    }
}

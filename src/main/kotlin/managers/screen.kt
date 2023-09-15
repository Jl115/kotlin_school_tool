/**
 * An enumeration to represent the different screens available in the application.
 * Each entry in the enum represents a screen and holds a label that describes it.
 */
enum class Screen(
    val label: String,
) {
    /** Represents the home screen with the label "Home". */
    HomeScreen(
        label = "Home",
    ),

    /** Represents the login screen with the label "Login". */
    LoginScreen(
        label = "Login",
    ),

    /** Represents the register screen with the label "Settings". */
    RegisterScreen(
        label = "Settings",
    ),
}

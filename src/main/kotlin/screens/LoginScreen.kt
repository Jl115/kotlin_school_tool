// Required imports for the functionalities used in the Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navcontroller.NavController
import screens.customComponents.languageButton
import screens.customComponents.navigationHeaderBar

/**
 * The LoginScreen Composable function.
 * It displays the login interface with email and password fields, a login button, language button, and register option.
 * @param navController The navigation controller to handle screen transitions.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun loginScreen(navController: NavController) {
    // State variables to hold email and password input
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }



    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF0ED))) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header bar with navigation controls
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationHeaderBar(true, isHomeScreen = false)
            }

            // Login form section
            Box(modifier = Modifier.width(700.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Login title
                    Text(
                        text = "Login",
                        modifier = Modifier.width(115.dp).height(60.dp),
                        style = TextStyle(
                            fontSize = 40.sp,
                            lineHeight = 60.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF1E1E1E),
                        )
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    // Email input field with label
                    Text(
                        text = "School E-mail",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 24.sp,
                            lineHeight = 48.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = email,
                        onValueChange = { newValue -> email = newValue },
                        label = {
                            Text(
                                text = "vorname.nachname@ict.csbe.ch",
                                style = TextStyle(
                                    fontSize = 30.sp,
                                    lineHeight = 30.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password input field with label
                    Text(
                        text = "Password",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 48.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = password,
                        onValueChange = { newValue -> password = newValue },
                        label = {
                            Text(
                                text = "School Password",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    lineHeight = 38.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(100.dp))

            // Buttons section at the bottom: Login and Language
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 150.dp, end = 20.dp, start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(80.dp))

                // Login button
                Button(
                    modifier = Modifier
                        .width(700.dp)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFE50010)),
                    onClick = {
                        if (isValidLogin(email, password)) {
                            println("Login erfolgreich!")
                            navController.navigate(Screen.HomeScreen.name)
                        } else {
                            println("Ungültige Anmeldedaten!")
                        }
                    }
                ) {
                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 38.sp,
                            fontWeight = FontWeight(500),
                            color = Color.White,
                        )
                    )
                }

                // Language change button
                languageButton()
            }

            // Register option with clickable underline effect
            Text(
                text = "Register",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { navController.navigate(Screen.RegisterScreen.name) },
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                )
            )
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Not a valid User")
            },
            text = {
                Text("Please just use the the Dummy Login test.test@ict.csbe.ch and test")
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}

/**
 * Validate the user's email and password.
 * @param email The user's email.
 * @param password The user's password.
 * @return True if the login credentials are valid, false otherwise.
 */
fun isValidLogin(email: String, password: String): Boolean {
    return email.endsWith("ict.csbe.ch") && password == "test" && email == "test.test@ict.csbe.ch"
}

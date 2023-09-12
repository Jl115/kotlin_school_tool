import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
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

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val languageFocusRequester = remember { FocusRequester() }
    val registerFocusRequester = remember { FocusRequester() }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFF0ED))
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationHeaderBar(true, isHomeScreen = false)
            }

            Box(modifier = Modifier.width(700.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Login",
                        modifier = Modifier
                            .width(115.dp)
                            .height(60.dp),
                        style = TextStyle(
                            fontSize = 40.sp,
                            lineHeight = 60.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF1E1E1E),
                        )
                    )

                    Spacer(modifier = Modifier.height(50.dp))

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(emailFocusRequester)
                            .focusOrder(emailFocusRequester) {
                                next = passwordFocusRequester
                                previous = registerFocusRequester
                            }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordFocusRequester)
                            .focusOrder(passwordFocusRequester) {
                                next = languageFocusRequester
                                previous = emailFocusRequester
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(100.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 150.dp, end = 20.dp, start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(80.dp))

                Button(
                    modifier = Modifier
                        .width(700.dp)
                        .height(80.dp)
                        .focusRequester(languageFocusRequester)
                        .focusOrder(languageFocusRequester) {
                            next = registerFocusRequester
                            previous = passwordFocusRequester
                        },
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

                languageButton()
            }
            Text(
                text = "Register",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { navController.navigate(Screen.RegisterScreen.name) }
                    .focusRequester(registerFocusRequester)
                    .focusOrder(registerFocusRequester) {
                        next = emailFocusRequester
                        previous = languageFocusRequester
                    },
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                )
            )
        }
    }
}

fun isValidLogin(email: String, password: String): Boolean {
    return email.endsWith("ict.csbe.ch") && password == "test" && email == "test.test@ict.csbe.ch"
}

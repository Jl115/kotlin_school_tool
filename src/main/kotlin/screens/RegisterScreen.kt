package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navcontroller.NavController

/**
 * The RegisterScreen Composable function.
 * It displays the registration interface with several input fields and dropdown menus to gather user details for registration.
 *
 * @param navController The navigation controller to handle screen transitions.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun registerScreen(
    navController: NavController
) {
    // State variables to hold user inputs during registration
    var expanded by remember { mutableStateOf(false) }
    var expandedApreanci by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }
    var selectedApreanci by remember { mutableStateOf("Infrormatiker 3 Jahre") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Constructing username using first name and last name
    val userName = "$firstName.$lastName"
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF0ED))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 100.dp, end = 100.dp, bottom = 50.dp, top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title text for the registration screen
            Text(
                text = "Register your CsBe Account",
                style = TextStyle(
                    fontSize = 40.sp,
                    lineHeight = 60.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF1E1E1E),
                )
            )

            // Container for the registration form
            Box(modifier = Modifier.width(1000.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Spacing between the title and the first input field
                    Spacer(modifier = Modifier.height(30.dp))

                    // Input field for First Name
                    Text(
                        text = "Firstname",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = firstName,
                        onValueChange = { newFirstName -> firstName = newFirstName.replace("\\s".toRegex(), "") },
                        label = {
                            Text(
                                text = "Your first Name",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 28.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Input field for Last Name
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Lastname",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = lastName,
                        onValueChange = { newLastName -> lastName = newLastName.replace("\\s".toRegex(), "") },
                        label = {
                            Text(
                                text = "Your last Name",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 28.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Gender selection dropdown
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Gender",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(Color(0.7F,0.7F,0.7F, 0.6f), shape = RoundedCornerShape(10))
                            .clickable { expanded = !expanded }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 30.dp, end = 30.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = selectedGender,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 28.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF7F7F7F),
                                    )
                                )
                                Icon(Icons.Outlined.ArrowDropDown, contentDescription = "dropdown Gender")
                            }
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            offset = DpOffset(x = 860.dp, y = 0.dp)
                        ) {
                            val reducedItList =
                                listOf("Male", "Female", "Non Binary", "Others")
                            for (i in reducedItList.indices) {
                                DropdownMenuItem(onClick = {
                                    selectedGender = reducedItList[i]
                                    expanded = false
                                }) {
                                    Text(reducedItList[i])
                                }
                            }
                        }
                    }

                    // Birth date input field
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Birthdate",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = birthDate,
                        onValueChange = { newBirthdate -> birthDate = newBirthdate },
                        label = {
                            Text(
                                text = "DD.MM.YYYY",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 28.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Apprenticeship selection dropdown
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Your Appreciate",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(Color(0.7F,0.7F,0.7F, 0.6f), shape = RoundedCornerShape(10))
                            .clickable { expandedApreanci = !expandedApreanci }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 30.dp, end = 30.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = selectedApreanci,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 28.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF7F7F7F),
                                    )
                                )
                                Icon(Icons.Outlined.ArrowDropDown, contentDescription = "dropdown Appreciate")
                            }
                        }
                        DropdownMenu(
                            expanded = expandedApreanci,
                            onDismissRequest = { expandedApreanci = false },
                            offset = DpOffset(x = 800.dp, y = 0.dp)
                        ) {
                            val apreanciList =
                                listOf("Informatiker 4 Jahre", "Informatiker 3 Jahre", "Mediamatiker", "10. Schuljahr")
                            for (i in apreanciList.indices) {
                                DropdownMenuItem(onClick = {
                                    selectedApreanci = apreanciList[i]
                                    expandedApreanci = false
                                }) {
                                    Text(apreanciList[i])
                                }
                            }
                        }
                    }

                    // Username display field
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Username",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = "",
                        readOnly = true,
                        onValueChange = { newFirstName -> firstName = newFirstName },
                        label = {
                            Text(
                                text = userName ,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 28.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Password input field
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "School Password",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    TextField(
                        value = password,
                        onValueChange = { newPassword -> password = newPassword },
                        label = {
                            Text(
                                text = "Your Password",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 28.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7F7F7F),
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Sign Up button
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        modifier = Modifier
                            .width(700.dp)
                            .height(80.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFE50010)),
                        onClick = {
                            // Birthdate validation and navigation logic
                            val parts = birthDate.split(".")
                            if (parts.size == 3) {
                                val day = parts[0].toIntOrNull()
                                val month = parts[1].toIntOrNull()
                                val year = parts[2].toIntOrNull()
                                if (day != null && month != null && year != null) {
                                    if (day in 1..31 && month in 1..12 && year in 1910..2023 && birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}".toRegex())) {
                                        navController.navigate(Screen.LoginScreen.name)
                                    } else {
                                        showDialog = true
                                    }
                                } else {
                                    showDialog = true
                                }
                            } else {
                                showDialog = true
                            }
                        }
                    ){
                        Text(
                            text = "Sign Up",
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 38.sp,
                                fontWeight = FontWeight(500),
                                color = Color.White,
                            )
                        )
                    }

                    // Error dialog for incorrect birthdate format
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showDialog = false
                            },
                            title = {
                                Text(text = "Not a valid Birthdate")
                            },
                            text = {
                                Text("Please Enter your Birthdate exact like this Format: DD.MM.YYYY")
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
            }
        }
    }
}

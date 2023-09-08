package screens

import Screen
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navcontroller.NavController
import screens.customComponents.navigationHeaderBar



    @Preview
    @Composable
    fun HomeScreen(
        navController: NavController
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            navigationHeaderBar(isLoginScreen = false )
            Button(
                modifier = Modifier.width(180.dp).height(60.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFE50010)),
                onClick = {
                    navController.navigate(Screen.LoginScreen.name)
                }
            ) {
                Text("back", color = Color.White)
            }
        }

        Column (
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Modul Notenn",
                style = TextStyle(
                    fontSize = 56.sp,
                    lineHeight = 84.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF1E1E1E),
                )
            )
            Box(){
                Column (
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    IconButton(
                        modifier = Modifier
                            .background(color = Color.Magenta, shape = CircleShape)
                            .height(80.dp)
                            .width(80.dp),
                        onClick = { /* Handle button click */ }
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null)
                    }
                    Text("Language")
                }

            }


        }

    }




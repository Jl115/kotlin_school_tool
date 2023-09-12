package screens.customComponents

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun languageButton() {
    val showDialog = remember { mutableStateOf(false) }
    var currentLanguage by remember { mutableStateOf("English") }

    var isEnglish by remember { mutableStateOf(true) }
    var isGerman by remember { mutableStateOf(false) }
    var isFrench by remember { mutableStateOf(false) }

    Box(){
        Column (
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFFFED00), shape = CircleShape)
                    .height(80.dp)
                    .width(80.dp)
                    .clip(CircleShape)
                    .clickable {
                        showDialog.value = true
                    }

            ) {
                Image(
                    painter = painterResource("images/language.png"),
                    contentDescription = "Language",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            if (isEnglish) {
                Text("Language")
            } else if (isGerman) {
                Text("Sprache")
            } else if (isFrench) {
                Text("Langue")
            }

        }
        if (showDialog.value) {
            Dialog(
                resizable = false,
                visible = showDialog.value,
                onCloseRequest = { showDialog.value = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(MaterialTheme.colors.background)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val languageList = listOf("Deutsch", "Französisch", "English")
                    for(i in languageList.indices) {
                        Button(modifier = Modifier.fillMaxWidth(), onClick = {
                            currentLanguage = languageList[i]
                            isEnglish = currentLanguage == "English"
                            isGerman = currentLanguage == "Deutsch"
                            isFrench = currentLanguage == "Französisch"
                            showDialog.value = false
                            println(currentLanguage)
                        }) {
                            Text(languageList[i])
                        }
                    }
                }
            }
        }
    }
}



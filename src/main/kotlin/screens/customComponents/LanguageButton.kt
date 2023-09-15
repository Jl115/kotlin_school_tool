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

/**
 * A Composable function that displays a button which triggers a dialog for selecting a language when clicked.
 * The selected language is reflected in the text displayed below the button.
 */
@Composable
fun languageButton() {
    // Remember whether to show the dialog or not. Initially, it is set to false.
    val showDialog = remember { mutableStateOf(false) }

    // Remember the current selected language. Initially, it is set to "English".
    var currentLanguage by remember { mutableStateOf("English") }

    // Remember the language preferences. Initially, English is selected, while German and French are not.
    var isEnglish by remember { mutableStateOf(true) }
    var isGerman by remember { mutableStateOf(false) }
    var isFrench by remember { mutableStateOf(false) }

    // A container that holds the button and the language text.
    Box(){
        Column (
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // A circular button with a yellow background that triggers the language selection dialog when clicked.
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
                // The language icon displayed at the center of the button.
                Image(
                    painter = painterResource("images/language.png"),
                    contentDescription = "Language",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            // Displays the word "Language" in the currently selected language.
            if (isEnglish) {
                Text("Language")
            } else if (isGerman) {
                Text("Sprache")
            } else if (isFrench) {
                Text("Langue")
            }
        }

        // Shows a dialog for selecting a language when showDialog is true.
        if (showDialog.value) {
            Dialog(
                resizable = false,
                visible = showDialog.value,
                onCloseRequest = { showDialog.value = false }
            ) {
                // A column that holds buttons for each available language.
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(MaterialTheme.colors.background)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // A list of available languages.
                    val languageList = listOf("Deutsch", "Französisch", "English")

                    // Creates a button for each language in the list.
                    // When a button is clicked, the current language is updated and the dialog is closed.
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

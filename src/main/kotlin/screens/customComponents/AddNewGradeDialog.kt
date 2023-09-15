package screens.customComponents

import androidx.compose.foundation.*
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import kotlin.math.round

/**
 * A Composable function to display a dialog for adding a new grade to a module.
 *
 * @param onClose A lambda function to be invoked when the dialog is closed without submitting.
 * @param onGradeAdded A lambda function to be invoked when a new grade is added, receiving the module name and grade as parameters.
 * @param isVisibleDialog A boolean to control the visibility of the dialog.
 */
@Composable
fun addNewGradeDialog(onClose: () -> Unit, onGradeAdded: (String, String) -> Unit, isVisibleDialog: Boolean) {
    // Remembering the state of the dialog with default width and height
    val windowState = rememberDialogState(height = 500.dp, width = 400.dp)

    // Defining mutable states to hold values for module name, dropdown menu state and grade
    var moduleName by remember { mutableStateOf("321") }
    var expandedSelectModule by remember { mutableStateOf(false) }
    var grade by remember { mutableStateOf(0.0f) }

    // A dialog component that is visible based on the `isVisibleDialog` parameter
    Dialog(
        state = windowState,
        onCloseRequest = onClose,
        visible = isVisibleDialog,
        title = "My Custom Sized Dialog"
    ) {
        // Main container to hold all the elements of the dialog
        Box(modifier = Modifier.fillMaxSize()) {
            // Column to arrange the elements vertically with specified padding and alignment
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colors.background)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Title text for the dialog with specified style properties
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp),
                    text = "New Module Grade",
                    style = TextStyle(
                        fontSize = 30.sp,
                        lineHeight = 35.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF1E1E1E),
                    )
                )
                // The following sections (module selection and grade input) are similar in structure:
                // - A label to indicate what the section is for
                // - A container to hold the input field and associated elements (like dropdown menu or slider)
                // - Styling elements like background color, padding, and text alignment are applied to enhance the UI
                Box(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            text = "Module",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF1E1E1E),
                                textAlign = TextAlign.Left
                            )
                        )
                        // Container for the dropdown menu and its trigger
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(color = Color.hsl(0f, 0f, 0.2f, 0.1f), shape = RoundedCornerShape(5))
                                .clickable { expandedSelectModule = true },
                        ) {
                            // Row to hold the selected module name and a dropdown arrow icon
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Displaying the currently selected module
                                Box(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(150.dp)
                                        .border(color = Color.LightGray, width = 0.1.dp, shape = RoundedCornerShape(5)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                        text = "Module: $moduleName"
                                    )
                                }
                                // Icon to indicate a dropdown menu
                                Icon(
                                    modifier = Modifier
                                        .padding(end = 20.dp),
                                    imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "Dropdown Modules"
                                )
                            }
                        }
                    }
                }
                // Dropdown menu for module selection with a list of predefined module names
                DropdownMenu(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .height(200.dp),
                    expanded = expandedSelectModule,
                    onDismissRequest = { expandedSelectModule = false },
                    offset = DpOffset(x = 220.dp, y = 280.dp)  // Adjust the offset to position the menu correctly
                ) {
                    // List of available modules to choose from
                    val reducedItList = listOf("123", "122", "162", "164", "198", "226", "123", "122", "162", "164", "198", "226")
                    for (i in reducedItList.indices) {
                        // Creating menu items for each module in the list
                        DropdownMenuItem(onClick = {
                            moduleName = reducedItList[i]
                            expandedSelectModule = false
                        }) {
                            Text(reducedItList[i])
                        }
                    }
                }

                // Spacer to add some vertical space between elements
                Spacer(modifier = Modifier.height(20.dp))

                // Container for the grade label
                Box(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp)) {
                    // Label for the grade input field
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        text = "Grade",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1E1E1E),
                            textAlign = TextAlign.Left
                        )
                    )
                }
                // Container for the grade input field and slider
                Box(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp)) {
                    // Container to hold the slider for grade input
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(color = Color.hsl(0f, 0f, 0.2f, 0.1f), shape = RoundedCornerShape(5)),
                    ) {
                        // Row to hold the grade display text and a slider
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Displaying the currently selected grade
                            Box(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(150.dp)
                                    .border(color = Color.LightGray, width = 0.1.dp, shape = RoundedCornerShape(5)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                    text = "Grade: ${round(grade * 10) / 10}"
                                )
                            }
                            // Slider to select a grade value
                            Slider(
                                modifier = Modifier
                                    .width(150.dp)
                                    .padding(end = 20.dp),
                                value = grade,
                                valueRange = 0f..6f,
                                steps = 60,
                                onValueChange = { grade = it }
                            )
                        }
                    }
                }

                // Spacer to add some vertical space between elements
                Spacer(modifier = Modifier.height(20.dp))

                // Row to hold the "Cancel" and "Submit" buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // "Cancel" button to close the dialog
                    Button(onClick = onClose) {
                        Text("Cancel")
                    }
                    // "Submit" button to submit the module name and grade, and close the dialog
                    Button(onClick = {
                        onGradeAdded(moduleName, grade.toString())
                        onClose()
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}

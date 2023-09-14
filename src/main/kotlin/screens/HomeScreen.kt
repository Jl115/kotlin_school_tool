package screens

import Screen
import androidx.compose.desktop.ui.tooling.preview.Preview
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import navcontroller.NavController
import screens.customComponents.Table
import screens.customComponents.addNewGradeDialog
import screens.customComponents.languageButton
import screens.customComponents.navigationHeaderBar


// Composable function for previewing homeScreen layout in Android Studio
@Preview
@Composable
fun homeScreen(
    navController: NavController // Navigation controller to handle navigation actions
) {
    // Remember and store whether the dropdown is expanded
    var expanded by remember { mutableStateOf(false) }
    val expandedAddGrade = remember { mutableStateOf(false) }
    val isDialogVisible by remember { mutableStateOf(true) }
    val dialogExcusedLesionVisible = remember { mutableStateOf(false) }
    val windowState = rememberDialogState(height = 500.dp, width = 550.dp)
    var timeExcused by remember { mutableStateOf(0) }
    var timeUnexcused by remember { mutableStateOf(0) }
    var timeExcusedAdded by remember { mutableStateOf(0) }
    var timeUnexcusedAdded by remember { mutableStateOf(0) }
    // Remember and store the selected semester value
    var selectedSemester by remember { mutableStateOf("Semester 1") }

    // Dummy data for note information
    val noteData = remember {
        mutableListOf(
            listOf("Module, 322", "Note: 6.0"),
            listOf("Module, 322", "Note: 6.0"),
            listOf("Module, 322", "Note: 6.0"),
            listOf("Module, 322", "Note: 6.0"),
            listOf("Module, 322", "Note: 6.0"),
            listOf("Module, 322", "Note: 6.0"),
        )
    }
    fun addGrade(module: String, note: String) {
        val checkList = listOf("Module: $module", "Grade: $note")
        if (!noteData.contains(checkList)) {
            noteData.add(checkList)
        }
    }


    // Dummy data for semester grade information
    val semesterGradeData = listOf(
        listOf("Modules, Grades", "Note: 6"),
        listOf("Ük, Grades", "Note: 6"),
        listOf("Excused lessons", "$timeExcusedAdded" + "L"),
        listOf("Unexcused lessons", "$timeUnexcusedAdded" + "L"),
    )
    // Main container Box with custom background color
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFF0ED))
    ) {
        // Column to arrange elements vertically with space between them and center alignment
        Column(
            modifier = Modifier.fillMaxSize().padding(26.dp).background(Color(0xFFFFF0ED)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Row for holding navigation bar and back button with space between them
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationHeaderBar(isLoginScreen = false, isHomeScreen = true)
                Button(
                    modifier = Modifier.width(120.dp).height(60.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFE50010)),
                    onClick = {
                        // Navigate to LoginScreen when the button is clicked
                        navController.navigate(Screen.LoginScreen.name)
                    }
                ) {
                    Text("back", color = Color.White)
                }
            }
            // Box to hold the semester selection elements with custom padding
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 200.dp, end = 200.dp)
            ) {
                // Row to hold text and dropdown menu for semester selection
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Modules Grades of Semester:",
                        style = TextStyle(
                            fontSize = 36.sp,
                            lineHeight = 36.sp,
                            fontWeight = FontWeight(300),
                            color = Color(0xFF1E1E1E),
                            textAlign = TextAlign.Left
                        )
                    )
                    Box(modifier = Modifier
                        .width(300.dp)
                        .height(40.dp)
                        .background(Color.White, shape = RoundedCornerShape(10))
                        .clickable { expanded = !expanded }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 30.dp, end = 30.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(selectedSemester)
                            Icon(Icons.Outlined.ArrowDropDown, contentDescription = "dropdown Semester")
                        }

                    }

                }
                // Dropdown menu with list of semesters
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(x = 1360.dp, y = 0.dp)  // Adjust the offset to position the menu correctly
                ) {
                    val reducedItList =
                        listOf("Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6")
                    for (i in reducedItList.indices) {
                        DropdownMenuItem(onClick = {
                            selectedSemester = reducedItList[i]
                            expanded = false
                        }) {
                            Text(reducedItList[i])
                        }
                    }
                }
            }
            // Invoke Table composable function to display note data in a table format
            Table(noteData)
            // Box to hold text with custom padding
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 200.dp, end = 200.dp)
            ) {
                Text(
                    text = "Semester Grades:",
                    style = TextStyle(
                        fontSize = 36.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Left
                    )
                )
            }
            // Invoke Table composable function to display semester grade data in a table format
            Table(semesterGradeData)

            // Row to hold the language button with alignment to the end
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.width(800.dp).padding(start = 200.dp, end = 200.dp).weight(1f)){
                    Row (modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround){
                        Box(modifier = Modifier
                            .width(300.dp)
                            .height(40.dp)
                            .padding(start = 20.dp)
                            .background(Color.White, shape = RoundedCornerShape(10))
                            .clickable { expandedAddGrade.value = true  }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 30.dp, end = 30.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Add Module Grade")
                                Icon(Icons.Outlined.ArrowDropDown, contentDescription = "Add Module Grade")
                            }
                        }
                        Box(modifier = Modifier
                            .width(300.dp)
                            .height(40.dp)
                            .background(Color.White, shape = RoundedCornerShape(10))
                            .clickable { dialogExcusedLesionVisible.value = true }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 30.dp, end = 30.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Add excused Lessons")
                                Icon(Icons.Outlined.ArrowDropDown, contentDescription = "Add excused Lessons")
                            }
                        }
                    }
                    if (expandedAddGrade.value) {
                        addNewGradeDialog(
                            onClose = { expandedAddGrade.value = false },
                            onGradeAdded = ::addGrade,
                            isVisibleDialog = isDialogVisible
                        )
                    }
                    Dialog(
                        state = windowState,
                        resizable = false,
                        visible = dialogExcusedLesionVisible.value,
                        onCloseRequest = { dialogExcusedLesionVisible.value = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .background(Color(0xFFFFF0ED))
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "Add Excused | Unexcused Lesson",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF1E1E1E),
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Column(modifier = Modifier
                                .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Box (modifier = Modifier. size(300.dp, 50.dp)){
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Excused Lesions: $timeExcused"+"L",
                                        style = TextStyle(
                                            fontSize = 22.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight(500),
                                            color = Color(0xFF1E1E1E),
                                            textAlign = TextAlign.Center
                                        ))
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Box (modifier = Modifier. size(300.dp, 50.dp)) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text= "Unexcused Lesions: $timeUnexcused"+"L",
                                        style = TextStyle(
                                            fontSize = 22.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight(500),
                                            color = Color(0xFF1E1E1E),
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row ( verticalAlignment = Alignment.CenterVertically){
                                Box (modifier = Modifier.padding(end = 30.dp)) {
                                    Text(
                                        text = "Excused Lesions Slider",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight(500),
                                            color = Color(0xFF1E1E1E),
                                        )
                                    )
                                }
                                Slider(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .padding(end = 20.dp),
                                    value = timeExcused.toFloat(),
                                    valueRange = 0f..10f,
                                    steps = 100,
                                    onValueChange = { timeExcused = it.toInt() }
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Row ( verticalAlignment = Alignment.CenterVertically){
                                Box (modifier = Modifier.padding(end = 30.dp)) {
                                    Text(
                                        text = " Excused Lesions Slider",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight(500),
                                            color = Color(0xFF1E1E1E),
                                        )
                                    )
                                }
                                Slider(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .padding(end = 20.dp),
                                    value = timeUnexcused.toFloat(),
                                    valueRange = 0f..10f,
                                    steps = 100,
                                    onValueChange = { timeUnexcused = it.toInt() }
                                )
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            Box(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(200.dp)
                                    .background(Color.White, shape = RoundedCornerShape(10))
                                    .clickable {
                                        timeExcusedAdded += timeExcused
                                        timeUnexcusedAdded += timeUnexcused
                                        dialogExcusedLesionVisible.value = false
                                    }
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 30.dp, end = 30.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text("Increase Time")
                                }
                            }

                            Spacer(modifier = Modifier.height(30.dp))

                            Box(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(200.dp)
                                    .background(Color.White, shape = RoundedCornerShape(10))
                                    .clickable {
                                        timeExcusedAdded -= timeExcused
                                        timeUnexcusedAdded -= timeUnexcused
                                        if (timeExcusedAdded < 0) {
                                            timeExcusedAdded = 0

                                        }else if ( timeUnexcusedAdded < 0) {
                                            timeUnexcusedAdded = 0
                                        }
                                        dialogExcusedLesionVisible.value = false
                                    }



                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 30.dp, end = 30.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text("Decrease Time")
                                }

                            }
                        }
                    }
                }
                languageButton()
            }
        }
    }

}













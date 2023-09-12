package screens.customComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Composable function to create a Table with given data
@Composable
fun Table(noteData: List<List<String>> = listOf()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 200.dp, end = 200.dp)
            .heightIn(max = 250.dp)

    ) {
        Column(
            modifier = Modifier
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(5))
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Loop over each row data and create rows with cells
            noteData.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // Loop over each cell data and create cells with border and background
                    row.forEach { cell ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 1.dp, end = 1.dp)
                                .background(Color.White)
                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10))
                                .height(50.dp)
                        ) {
                            Text(
                                text = cell,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterStart)
                                    .padding(start = 30.dp)

                            )
                        }
                    }
                }
            }
        }
    }
}
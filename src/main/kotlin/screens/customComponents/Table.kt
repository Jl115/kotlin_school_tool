package screens.customComponents;

import androidx.compose.foundation.background;
import androidx.compose.foundation.border;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.rememberScrollState;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.verticalScroll;
import androidx.compose.material.Text;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.dp;

/**
 * A Composable function to create a table with the given data.
 *
 * @param noteData A list of lists of strings representing the data to be displayed in the table.
 *                 Each list of strings represents a row in the table and each string in the inner list
 *                 represents a cell in that row.
 */
@Composable
fun table(noteData: List<List<String>> = listOf()) {
    // A container that takes up the maximum width and has a defined maximum height and padding.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 200.dp, end = 200.dp)
            .heightIn(max = 250.dp)
    ) {
        // A column that contains rows of data, with a gray border and rounded corners,
        // and allows vertical scrolling.
        Column(
            modifier = Modifier
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(5))
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Loop over each row data to create rows with cells.
            noteData.forEach { row ->
                // A row that takes up the maximum width and has evenly spaced cells with defined padding.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // Loop over each cell data to create cells with a white background, light gray border, and defined height.
                    row.forEach { cell ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 1.dp, end = 1.dp)
                                .background(Color.White)
                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10))
                                .height(50.dp)
                        ) {
                            // A text element that displays the cell data, aligned to the start and with defined padding.
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

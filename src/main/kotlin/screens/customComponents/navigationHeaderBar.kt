package screens.customComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navcontroller.NavController

/**
 * A Composable function that displays a navigation header bar with a logo and text. Additional spacing is added on the login screen.
 *
 * @param isLoginScreen A boolean flag indicating if the current screen is the login screen.
 * @param isHomeScreen A boolean flag indicating if the current screen is the home screen.
 */
@Composable
fun navigationHeaderBar(
    isLoginScreen: Boolean,
    isHomeScreen: Boolean,
) {
    // A circular container with a yellow background that holds the logo.
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color(0xFFFFED00))
    ) {
        // Displays the CSB logo in the center of the circle.
        Image(
            painter = painterResource("images/csbLogo.png"),
            contentDescription = "Logo of Computer School Bern",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }

    // Displays the text "Computer Schule Bern" with specified style properties.
    Text(
        text = "Computer Schule Bern",
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF000000),
        )
    )

    // Adds additional spacing on the login screen.
    if (isLoginScreen) {
        Spacer(modifier = Modifier.width(20.dp))
    }
}

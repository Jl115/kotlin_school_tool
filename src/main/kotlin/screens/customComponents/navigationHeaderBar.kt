package screens.customComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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



@Composable
fun navigationHeaderBar(
    isLoginScreen: Boolean,
    isHomeScreen: Boolean,
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color(0xFFFFED00))
    ) {
        Image(
            painter = painterResource("images/csbLogo.png"),
            contentDescription = "Logo of Computer School Bern",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }

    Text(
        text = "Computer Schule Bern",
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF000000),
        )
    )
    if (isLoginScreen) {
        Spacer(modifier = Modifier.width(20.dp))
    }
}
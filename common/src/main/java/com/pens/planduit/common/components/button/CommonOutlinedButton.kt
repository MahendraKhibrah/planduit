package com.pens.planduit.common.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.theme.MediumBlack

@Composable
fun CommonOutlinedButton(
    onPressed : () -> Unit,
    width : Int? = null,
    title : String = "Ulangi Pertanyaan"
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GradientContainer(
            gradientColors = listOf(Color.Transparent),
            borderColor = Color.Black,
            onPressed = onPressed,
            modifier = Modifier.width(width?.dp ?: screenWidth.times(0.8f))
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = title,
                    style = MediumBlack
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
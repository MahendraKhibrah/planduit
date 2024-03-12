package com.pens.planduit.presentation.features.main.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.commonItemGradient

@Composable
fun MenuItem(
    title : String,
    imageId : Int,
    onPressed : () -> Unit = {}
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(83.dp)
    ){
        GradientContainer(
            gradientColors = commonItemGradient,
            onPressed = onPressed,
            isGradientVertical = true,
            borderColor = GreenPrimary,
            cornerRadius = 14
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.sizeIn(minWidth = 50.dp, minHeight = 50.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = title, style = MediumBlack.copy(fontSize = 10.sp), textAlign = TextAlign.Center)
    }
}
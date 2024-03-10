package com.pens.planduit.common.components.container

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.commonItemGradient

@Composable
fun GradientContainer(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 8,
    gradientColors: List<Color>,
    borderColor: Color = Color.Transparent,
    borderWidth: Double = 1.2,
    isGradientVertical: Boolean = false,
    showShadow: Boolean = false,
    elevation: Int = 3,
    padding: Int = 8,
    onPressed : () -> Unit = {},
    child: @Composable () -> Unit,
) {
    val shape: Shape = RoundedCornerShape(cornerRadius.dp)

    Box(
        modifier = modifier
            .then(if (showShadow) Modifier.shadow(elevation.dp, shape) else Modifier)
            .then(
                if (gradientColors.size == 1) Modifier.background(
                    gradientColors[0],
                    shape
                ) else Modifier.background(
                    brush = if (isGradientVertical) {
                        Brush.verticalGradient(gradientColors, 0f, 100f)
                    } else {
                        Brush.horizontalGradient(gradientColors, 0f, 100f)
                    },
                    shape = shape
                )
            )
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius.dp)
            )
            .clickable { onPressed() }
            .padding(padding.dp)
    ) {
        child()
    }
}

//@Composable
//@Preview(showBackground = true)
//fun GradientContainerPreview() {
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    Box(
//        modifier = Modifier.size(width = screenWidth, height = screenHeight)
//    ) {
//        GradientContainer(
//            gradientColors = commonItemGradient,
//            showShadow = true,
//            borderColor = GreenPrimary,
//            isGradientVertical = true,
//            child = {
//                Text(text = "Hello \nWorld")
//            }
//        )
//    }
//}

//@Composable
//@Preview(showBackground = true)
//fun CompactContainerPreview() {
//    Box(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        GradientContainer(
//            gradientColors = listOf(GreenPrimary),
//            borderColor = GreenPrimary,
//            child = {
//                Text(text = "Hello World")
//            }
//        )
//    }
//}

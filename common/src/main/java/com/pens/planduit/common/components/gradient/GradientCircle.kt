package com.pens.planduit.common.components.gradient

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pens.planduit.common.theme.PaleBlue

@Composable
fun GradientCircle(color: Color, opacity : Float = 0.6f) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .size(width = screenWidth.times(1.4f), height = screenWidth.times(1.4f))
            .background(Color.Transparent)
    )
    {
        Canvas(
            modifier = Modifier
                .aspectRatio(1f)
        ) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = size.minDimension
            val gradient = Brush.radialGradient(
                colors = listOf(
                    color.copy(alpha = opacity),
                    color.copy(alpha = 0f)
                ),
                center = center,
                radius = radius
            )
            drawCircle(brush = gradient, center = center, radius = radius)
        }
    }
}

//@Preview
//@Composable
//fun PreviewGradientCircle() {
//        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    Box(
//        modifier = Modifier
//            .size(width = screenWidth, height = screenHeight)
//            .background(Color.White)
//    ) {
//        GradientCircle(color = PaleBlue)
//    }
//}

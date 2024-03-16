package com.pens.planduit.common.components.container

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.pens.planduit.common.theme.GreenPrimary

@Composable
fun PlanDuitPoint(
    pointAmount : Int,
    spacerSize : Int = 16
) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(((40*pointAmount) + (spacerSize * (pointAmount - 1))).dp)
    ) {
        Column(modifier = Modifier.width(20.dp)) {
            for (i in 0 until pointAmount - 1) {
                GreenCircle()
                Spacer(modifier = Modifier.size(spacerSize.dp))
            }
            GreenCircle()

        }
        Line()
    }
}

@Composable
fun GreenCircle() {
    Canvas(modifier = Modifier.size(40.dp)) {
        drawCircle(color = GreenPrimary, radius = 20f)
    }
}

@Composable
fun Line() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            color = GreenPrimary,
            start = Offset(size.width / 2, 60f),
            end = Offset(size.width / 2, size.height - 40),
            strokeWidth = 5f
        )
    }
}
package com.pens.planduit.common.components.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pens.planduit.common.components.appBar.PlanDuitAppBar
import com.pens.planduit.common.components.gradient.GradientCircle
import com.pens.planduit.common.theme.GreenSecondary
import com.pens.planduit.common.theme.PaleBlue

@Composable
fun PlanDuitScaffold(
    content: @Composable () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .size(width = screenWidth, height = screenHeight)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier.offset(x = screenWidth.times(0.9f) * -1, y = screenHeight.times(0.23f))
        ) {
            GradientCircle(color = GreenSecondary)
        }
        Box(
            modifier = Modifier.offset(x = screenWidth.times(0.5f), y = screenHeight.times(0.88f))
        ) {
            GradientCircle(color = PaleBlue)
        }
        Column {
            PlanDuitAppBar (
                title = "PlanDuit",
            )
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                content()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlanDuitScaffoldPreview() {
//    PlanDuitScaffold {
//        Column {
//            Box(
//                modifier = Modifier
//                    .size(500.dp)
//                    .background(GreenSecondary)
//            )
//            Spacer(modifier = Modifier.size(16.dp))
//            Box(
//                modifier = Modifier
//                    .size(500.dp)
//                    .background(GreenSecondary)
//            )
//        }
//    }
//}
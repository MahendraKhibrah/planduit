package com.pens.planduit.common.components.container

import android.util.Log
import androidx.compose.foundation.ScrollState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    showAppBar : Boolean = true,
    title : String = "",
    trailingWidget : @Composable () -> Unit = {},
    onBackPressed : () -> Unit = {},
    stickyHeader : @Composable () -> Unit = {},
    bottomSheet : @Composable () -> Unit = {},
    scrollState: ScrollState = rememberScrollState(),
    hideBackButton : Boolean = false,
    hideContentPadding : Boolean = false,
    content: @Composable () -> Unit,
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
            if(showAppBar) PlanDuitAppBar (
                title = title,
                trailingWidget = trailingWidget,
                hideLeading = hideBackButton,
                onBackAction = onBackPressed
            )
            stickyHeader()
            Box(
                modifier = Modifier
                    .padding(
                        start = if(hideContentPadding)0.dp else 24.dp,
                        end = if(hideContentPadding)0.dp else 24.dp,
                        bottom = if(hideContentPadding)0.dp else 24.dp
                    )
                    .verticalScroll(
                        scrollState
                    )
            ) {
                content()
            }
        }
    }
    bottomSheet()
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
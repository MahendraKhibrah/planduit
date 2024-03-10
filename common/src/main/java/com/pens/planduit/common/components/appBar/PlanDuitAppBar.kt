package com.pens.planduit.common.components.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.SmallBlack

@Composable
fun PlanDuitAppBar(
    modifier: Modifier = Modifier,
    hideLeading: Boolean = false,
    title: String = "",
    onBackAction: () -> Unit = {},
    trailingWidget: @Composable () -> Unit = {}
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = modifier
            .size(width = screenWidth, height = 56.dp)
            .background(color = Color.White)
    ) {
        if (!hideLeading) IconButton(
            onClick = onBackAction,
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "", tint = Color.Black, modifier = Modifier.size(30.dp))
        } else Box {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = title, style = MediumBlack)
            Spacer(modifier = Modifier.weight(1f))

        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
        ){
            Spacer(modifier = Modifier.weight(1f))
            trailingWidget()
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlanDuitAppBarPreview() {
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    Box(
//        modifier = Modifier.size(
//            width = screenWidth,
//            height = screenHeight
//        ).background(Color.White)
//    ) {
//        PlanDuitAppBar(
//            title = "Artikel Investasi",
//            onBackAction = {},
//        )
//    }
//}


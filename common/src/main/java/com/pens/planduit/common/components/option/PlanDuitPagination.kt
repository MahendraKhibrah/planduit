package com.pens.planduit.common.components.option

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BalanceWhite
import com.pens.planduit.common.theme.MalachiteGreen

@Composable
fun PlanDuitPagination(
    modifier: Modifier = Modifier,
    pageCount: Int,
    onChanged: (Int) -> Unit = {}
) {
    var currentPage by remember { mutableIntStateOf(1) }
    var firstPage by remember { mutableIntStateOf(1) }
    var lastPage by remember { mutableIntStateOf(pageCount + 1) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(4.dp)
    ) {
        if (currentPage != 1) IconButton(
            onClick = {
                if ((currentPage - 1) < firstPage) {
                    firstPage -= 1
                }
                currentPage -= 1
                onChanged(currentPage)
            },
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(46.dp)
            )
        } else
            Box(
                modifier = Modifier.size(48.dp)
            )

        (firstPage until if (lastPage < 9) lastPage else (firstPage + 7)).forEach { index ->
            GradientContainer(
                gradientColors = if (index == currentPage) listOf(MalachiteGreen) else listOf(Color.Transparent),
                onPressed = {
                    currentPage = index
                    onChanged(index)
                },
            ) {
                Text(
                    text = index.toString(),
                    style = if (index == currentPage) BalanceWhite else BalanceBlack,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        if (currentPage != pageCount) IconButton(
            onClick = {
                if ((currentPage + 1) > firstPage + 6) {
                    firstPage += 1
                }
                currentPage += 1
                onChanged(currentPage)


            },
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(46.dp)
            )
        } else
            Box(
                modifier = Modifier.size(46.dp)
            )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewPlanDuitPagination() {
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    Box(
//        modifier = Modifier
//            .size(
//                width = screenWidth,
//                height = screenHeight
//            )
//            .background(Color.White)
//    ) {
//        PlanDuitPagination(
//            pageCount = 7
//        )
//    }
//}
package com.pens.planduit.common.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.MalachiteGreen
import com.pens.planduit.common.theme.SmallBlack
import java.time.format.TextStyle


@Composable
fun PlanDuitCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    text: String = "",
    textStyle: androidx.compose.ui.text.TextStyle = SmallBlack,
    onTap: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clickable { onTap() }
                .clip(CircleShape)
                .size(20.dp)
                .background(MalachiteGreen)
                .padding((1.8).dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(MalachiteGreen)
                        .padding(2.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = textStyle
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlanDuitCheckBoxPreview() {
//    val isChecked = remember { mutableStateOf(false) }
//
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//
//    Box(
//        modifier = Modifier
//            .size(width = screenWidth, height = screenHeight)
//            .background(Color.White)
//    ) {
//        PlanDuitCheckBox(
//            isChecked = isChecked,
//            text = "Check me lorem ipsum dolor sit amet consectetur adipiscing elit",
//        )
//    }
//}


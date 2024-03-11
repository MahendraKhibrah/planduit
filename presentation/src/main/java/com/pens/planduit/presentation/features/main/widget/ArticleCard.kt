package com.pens.planduit.presentation.features.main.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BalanceGrey
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.MediumBlack

@Composable
fun ArticleCard(
    title : String,
    description : String,
    date : String,
    hideDivider : Boolean = false
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column {
        Row {
            Column(
                modifier = androidx.compose.ui.Modifier.width(screenWidth.times(0.55f))
            ) {
                Text(
                    text = title,
                    style = MediumBlack.copy(fontSize = 11.sp),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description,
                    style = BalanceBlack.copy(fontSize = 10.sp),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = date,
                    style = BalanceGrey.copy(fontSize = 11.sp),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(83.dp)
                    .background(DarkGrey, shape = RoundedCornerShape(8.dp))
            )
        }
        if (!hideDivider) Spacer(modifier = Modifier.height(32.dp))
        if (!hideDivider) HorizontalDivider(
            color = Color(0xFFEDEDED)
        )
        Spacer(modifier = Modifier.height(if (!hideDivider) 32.dp else 16.dp))
    }
}
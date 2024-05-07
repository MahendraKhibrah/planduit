package com.pens.planduit.presentation.features.article.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BalanceGrey
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.utils.Utils

@Composable
fun ArticleCard(
    title : String,
    description : String,
    date : String,
    hideDivider : Boolean = false,
    thumbnailUrl : String,
    onLoading : Boolean = false,
    onTap : () -> Unit = {}
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val interactionSource = remember { MutableInteractionSource() }
    var errorLoadImage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.clickable(
            onClick = onTap,
            interactionSource = interactionSource,
            indication = null
        )
    ) {
        Row {
            Column(
                modifier = androidx.compose.ui.Modifier.width(screenWidth.times(0.55f))
            ) {
                if(onLoading){
                    ShimmerBox(width = screenWidth.times(0.4f), height = Utils.getTextHeight(11f))
                } else {
                    Text(
                        text = title,
                        style = MediumBlack.copy(fontSize = 11.sp),
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (onLoading) {
                    ShimmerBox(width = screenWidth.times(0.55f), height = Utils.getTextHeight(10f))
                } else {
                    Text(
                        text = description,
                        style = BalanceBlack.copy(fontSize = 10.sp),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (onLoading) {
                    ShimmerBox(width = screenWidth.times(0.3f), height = Utils.getTextHeight(10f))
                }
                else {
                    Text(
                        text = date,
                        style = BalanceGrey.copy(fontSize = 11.sp),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (onLoading) {
                ShimmerBox(width = 83.dp, height = 83.dp)
            } else if (errorLoadImage) {
                Box(
                    modifier = Modifier
                        .size(83.dp)
                        .background(DarkGrey, shape = RoundedCornerShape(8.dp))
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(thumbnailUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(83.dp)
                        .clip(
                            RoundedCornerShape(8)
                        ),
                    onError = {
                        errorLoadImage = true
                    }
                )
            }
        }
        if (!hideDivider) Spacer(modifier = Modifier.height(28.dp))
        if (!hideDivider) HorizontalDivider(
            color = Color(0xFFEDEDED)
        )
        Spacer(modifier = Modifier.height(if (!hideDivider) 28.dp else 16.dp))
    }
}
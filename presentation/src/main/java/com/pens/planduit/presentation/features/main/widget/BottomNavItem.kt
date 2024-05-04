package com.pens.planduit.presentation.features.main.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.MediumGrey

@Composable
fun BottomNavItem(
    isActive : Boolean = false,
    onClick: () -> Unit,
    icons : List<Int>,
    title : String
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .width(screenWidth / 3)
            .height(100.dp)
            .background(color = Color.White)
            .clickable { onClick() }
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier
                .width(screenWidth / 3)
                .height(6.dp)
                .background(color = if(isActive) GreenPrimary else Color.White))
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = if(isActive) icons[1] else icons[0]),
                contentDescription = null,
                modifier = Modifier
                    .sizeIn(minWidth = 30.dp, minHeight = 30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = title, style = MediumGrey.copy(
                color = if(isActive) GreenPrimary else HalfGrey,
                fontSize = 14.sp
            ))
        }
    }
}
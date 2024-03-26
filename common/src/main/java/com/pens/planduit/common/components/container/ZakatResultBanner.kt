package com.pens.planduit.common.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.RedPrimary
import com.pens.planduit.common.utils.Utils

@Composable
fun ZakatResultBanner(
    isLoading: Boolean = false,
    isSuccess: Boolean? = false,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    if (isLoading) {
        ShimmerBox(width = screenWidth, height = 65.dp)
    } else {
        GradientContainer(
            gradientColors = listOf(if (isSuccess != false) PaleBlue else RedPrimary),
            cornerRadius = 19
        ) {
            Column(
                modifier = Modifier
                    .width(screenWidth)
                    .height(65.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.width(screenWidth.times(0.8f))
                ) {
                    Text(
                        text = if (isSuccess != false) "Kamu wajib membayar zakat penghasilan" else "Kamu tidak perlu membayar zakat penghasilan",
                        style = MediumBlack.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}
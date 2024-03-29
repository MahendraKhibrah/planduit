package com.pens.planduit.presentation.features.zakatSavings.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.R
import com.pens.planduit.common.components.button.CommonOutlinedButton
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.container.ZakatResultBanner
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.components.textField.ShortTextField
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SavingsBottomSheet
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatIncome.view.CommonPrice
import com.pens.planduit.presentation.features.zakatIncome.view.ResultSection
import com.pens.planduit.presentation.navigation.AppRoute


@Preview(showBackground = true)
@Composable
fun ZSavingsResultPage(
) {
    var showBottomSheet by remember { mutableStateOf(false) }


//    LaunchedEffect(true) {
//        viewModel.getRicePrice()
//    }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Tabungan",
        onBackPressed = {
//            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = SavingsBottomSheet,
                isOpen = showBottomSheet,
                onDismiss = {
                    showBottomSheet = false
                }
            )
        },
        trailingWidget = {
            val interactionSource =
                remember { MutableInteractionSource() }

            Image(
                painter = painterResource(id = R.drawable.ic_question_mark),
                contentDescription = null,
                modifier = Modifier
                    .sizeIn(minWidth = 30.dp, minHeight = 30.dp)
                    .clickable(
                        onClick = {
                            showBottomSheet = true
                        },
                        interactionSource = interactionSource,
                        indication = null
                    )
            )
        }
    ) {
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            Banner(state = GoldPriceState(data = GoldPrice(0), isLoading = false))
            Spacer(modifier = Modifier.size(32.dp))
            Text(text = "TABUNGAN KAMU SAAT INI", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(
                price = 10000,
                isLoading = false
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "BUNGA YANG KAMU DAPAT DARI BANK",
                style = SmallBlack.copy(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(
                price = 10000,
                isLoading = false,
                customTitle = "10 %"
            )
            Spacer(modifier = Modifier.height(24.dp))
            ResultSection(isLoading = false, price = 10000)
            Spacer(modifier = Modifier.height(100.dp))
            ZakatResultBanner(
                isLoading = false,
                isSuccess = false,
                title = "Tabungan"
            )
            Spacer(modifier = Modifier.height(24.dp))
            CommonOutlinedButton(onPressed = {})
        }
    }
}

@Composable
private fun Banner(
    state: GoldPriceState
) {
    GradientContainer(
        gradientColors = listOf(PaleBlue, PaleBlue.copy(alpha = 0.8f)),
        showShadow = true,
        cornerRadius = 16,
    ) {

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val textWidth = Utils.getTextWidth(fontSize = 25f, textLength = 10)
        val textHeight = Utils.getTextHeight(fontSize = 25f)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 28.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(screenWidth.times(0.5f))
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    "Harga Emas Hari ini",
                    style = SmallBlack.copy(fontSize = 15.sp, color = Color(0xFF606060))
                )
                if (state.isLoading) {
                    ShimmerBox(width = textWidth, height = textHeight)
                } else {
                    Text(
                        "Rp. " + Utils.addCommasEveryThreeChars(state.data.price),
                        style = LeadingGreen.copy(fontSize = 25.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.gold_price),
                contentDescription = null,
                modifier = Modifier.sizeIn(minWidth = 80.dp, minHeight = 80.dp)
            )
        }

    }
}
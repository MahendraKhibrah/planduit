package com.pens.planduit.presentation.features.zakatGold.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.container.ZakatResultBanner
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GoldBottomSheet
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.OffGreen
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.zakatGold.viewModel.ZGoldViewModel
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatIncome.view.CommonPrice
import com.pens.planduit.presentation.features.zakatIncome.view.ResultSection

@Composable
fun ZakatGoldPage(
    navController: NavController,
    viewModel: ZGoldViewModel = hiltViewModel<ZGoldViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val textValue = viewModel.textFieldValue.collectAsStateWithLifecycle()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val state = viewModel.state.collectAsStateWithLifecycle()
    val resultState = viewModel.resultState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getGoldPrice()
    }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Emas",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = GoldBottomSheet,
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
            Banner(state = state.value)
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = "jumlah emas (per gram)",
                style = MediumBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                RpTextField(
                    onValueChange = {
                        viewModel.textFieldValue.value = it
                    },
                    hideLeading = true,
                    modifier = Modifier.width(screenWidth.times(0.62f))
                )
                Spacer(modifier = Modifier.weight(1f))
                GradientContainer(
                    gradientColors = listOf(if (textValue.value.isNotEmpty() && !state.value.isLoading) GreenPrimary else HalfGrey),
                    modifier = Modifier.size(screenWidth.times(0.23f), 55.dp),
                    cornerRadius = 12,
                    onPressed = {
                        viewModel.getGoldZakatCalculation()
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Hitung",
                            style = MediumWhite.copy(color = if (textValue.value.isNotEmpty() && !state.value.isLoading) Color.White else DarkGrey)
                        )
                    }
                }
            }
            if (resultState.value.zakatRequest != 0){
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "JUMLAH EMAS YANG KAMU MILIKI", style = SmallBlack.copy(fontSize = 14.sp))
                Spacer(modifier = Modifier.height(8.dp))
                CommonPrice(price = resultState.value.zakatRequest, isLoading = resultState.value.isLoading, customTitle = "${resultState.value.zakatRequest} gram")
                if (resultState.value.data.status) {
                    Spacer(modifier = Modifier.height(24.dp))
                    ResultSection(isLoading = resultState.value.isLoading, price = state.value.data.price)
                }
            }
            Spacer(modifier = Modifier.height(100.dp))

            if(resultState.value.zakatRequest!= 0) ZakatResultBanner(isLoading = resultState.value.isLoading, isSuccess = resultState.value.data.status, title = "emas")
        }
    }
}

@Composable
private fun Banner(
    state: GoldPriceState
) {
    GradientContainer(
        gradientColors = listOf(PaleBlue, OffGreen),
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
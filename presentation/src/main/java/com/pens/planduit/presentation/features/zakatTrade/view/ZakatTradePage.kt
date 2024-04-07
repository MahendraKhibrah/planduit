package com.pens.planduit.presentation.features.zakatTrade.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.components.textField.ShortTextField
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.OffGreen
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SavingsBottomSheet
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.theme.TradingBottomSheet
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatTrade.viewModel.ZakatTradeViewModel
import com.pens.planduit.presentation.navigation.AppRoute


@Composable
fun ZakatTradePage(
    navController: NavController,
    viewModel: ZakatTradeViewModel = hiltViewModel<ZakatTradeViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsStateWithLifecycle()
    val fieldValueState = viewModel.fieldValueState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getGoldPrice()
    }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Perdagangan",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = TradingBottomSheet,
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
            Banner(
                state = state.value,
            )
            Spacer(modifier = Modifier.size(12.dp))
            CommonField(title = "Berapa modal yang kamu putar ?", onDone = {
                viewModel.changeFieldValue(0, it)
            }, value = fieldValueState.value[0])
            if (viewModel.isShowField(1)) {
                CommonField(title = "Berapa piutang lancar kamu ?", onDone = {
                    viewModel.changeFieldValue(1, it)
                }, value = fieldValueState.value[1])
            }
            if (viewModel.isShowField(2)) {
                CommonField(title = "Berapa keuntungan perdagangan kamu ?", onDone = {
                    viewModel.changeFieldValue(2, it)
                }, value = fieldValueState.value[2])
            }
            if (viewModel.isShowField(3)) {
                CommonField(title = "Berapa hutang jatuh tempo kamu ?", onDone = {
                    viewModel.changeFieldValue(3, it)
                }, value = fieldValueState.value[3])
            }
            if (viewModel.isShowField(4)) {
                CommonField(title = "Berapa kerugian perdagangan kamu ?", onDone = {
                    viewModel.changeFieldValue(4, it)
                }, value = fieldValueState.value[4])
            }
            Spacer(modifier = Modifier.size(30.dp))
            SubmitButton(isActive = viewModel.isShowField(5)) {
                navController.navigate(AppRoute.ZakatTradeResult.withArgs(viewModel.getRequestString(), state.value.data.price))
            }
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
                        "Rp. " + Utils.addCommasEveryThreeChars(state.data.price.toLong()),
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

@Composable
private fun SubmitButton(
    isActive: Boolean = false,
    onPressed: () -> Unit = {},
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        GradientContainer(
            gradientColors = listOf(if (isActive) GreenPrimary else HalfGrey),
            modifier = Modifier.size(screenWidth, 45.dp),
            cornerRadius = 12,
            onPressed = {
                if (isActive) {
                    onPressed()
                }

            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Lihat Zakat Perdaganganmu",
                    style = MediumWhite.copy(color = if (isActive) Color.White else DarkGrey)
                )
            }
        }
    }
}

@Composable
private fun CommonField(
    title: String,
    value: String = "",
    onDone: (String) -> Unit = {},
) {
    Spacer(modifier = Modifier.size(20.dp))
    Text(
        text = title,
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(8.dp))
    RpTextField(onDone = onDone, value = value)
}
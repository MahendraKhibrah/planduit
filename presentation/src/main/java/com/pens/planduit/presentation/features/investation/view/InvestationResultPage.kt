package com.pens.planduit.presentation.features.investation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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
import com.pens.planduit.common.theme.BoldBlue
import com.pens.planduit.common.theme.BoldRed
import com.pens.planduit.common.theme.BoldYellow
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.InvestmentBottomSheet
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumCrossedRed
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.RedPrimary
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.investation.viewModel.InvestmentResultViewModel
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun InvestationResultPage(
    navController: NavController,
    viewModel: InvestmentResultViewModel = hiltViewModel<InvestmentResultViewModel>(),
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var showBottomSheet by remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsStateWithLifecycle()

    val textWidth = (13 * 14.sp.value * 0.6f).dp
    val textHeight = (14.sp.value * 1.5f).dp.minus(2.dp)

    var isRecomendationShow by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.getInvestmentResult()
    }


    PlanDuitScaffold(
        title = "Kalkulator Investasi",
        bottomSheet = {
            CommonBottomSheet(
                data = InvestmentBottomSheet,
                isOpen = showBottomSheet,
                onDismiss = {
                    showBottomSheet = false
                }
            )
        },
        onBackPressed = {
            navController.popBackStack()
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
            if (state.value.isLoading)
                ShimmerBox(width = screenWidth, height = 115.dp, cornerRadius = 16)
            else GradientContainer(
                gradientColors = listOf(if (state.value.investmentResult?.isSuccess == false) RedPrimary else PaleBlue),
                showShadow = true,
                cornerRadius = 16
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.width(screenWidth.times(0.6f))
                    ) {
                        Text(
                            if (state.value.investmentResult?.isSuccess == false) "Yah, strategimu belum cocok untuk mencapai target investasimu"
                            else "Yeay! strategimu cocok untuk mencapai target investasimu",
                            style = MediumBlack.copy(fontSize = 14.sp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = null,
                        modifier = Modifier.height(100.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(32.dp))
            CommonSection(
                "TARGET YANG INGIN KAMU CAPAI",
                Utils.createRpText(
                    state.value.investmentRequest?.targetMoney,
                ),
                state.value.isLoading
            )
            CommonSection(
                "UANG YANG ADA SAAT INI",
                Utils.createRpText(state.value.investmentRequest?.initialMoney),
                state.value.isLoading
            )
            CommonSection(
                "JUMLAH INVESTASI KAMU",
                Utils.createRpText(state.value.investmentRequest?.moneyInvestment),
                state.value.isLoading
            )
            CommonSection(
                "RETURN YANG DIHARAPKAN/TAHUN",
                Utils.createPercentText(state.value.investmentRequest?.interest),
                state.value.isLoading
            )
            InvestationTempoSection(
                state.value.investmentRequest?.targetTime ?: 0,
                state.value.investmentResult?.recommendation?.year ?: 0,
                isRecomendationShow,
                state.value.investmentResult?.isSuccess ?: true,
                state.value.isLoading
            )
            Text(text = "HASIL RENCANA KAMU", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_money_bag),
                    contentDescription = null,
                    modifier = Modifier.size(21.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                if (state.value.isLoading)
                    ShimmerBox(width = textWidth, height = textHeight)
                else
                    Text(
                        text = Utils.createRpText(state.value.investmentResult?.result),
                        style = MediumBlack.copy(fontSize = 14.sp)
                    )
            }
            Spacer(modifier = Modifier.size(24.dp))
            if (isRecomendationShow) InvestationDetailSection(
                (state.value.investmentResult?.recommendation?.investPrimaryPercentage)?.toFloat() ?: 0f,
                state.value.investmentResult?.recommendation?.investPrimary ?: 0,
                state.value.investmentResult?.recommendation?.investInterest ?: 0,
                (state.value.investmentResult?.recommendation?.investInterestPercentage)?.toFloat() ?: 0f,
                state.value.isLoading
            ) else InvestationDetailSection(
                (state.value.investmentResult?.investPrimaryPercentage)?.toFloat() ?: 0f,
                state.value.investmentResult?.investPrimary ?: 0,
                state.value.investmentResult?.investInterest ?: 0,
                (state.value.investmentResult?.investInterestPercentage)?.toFloat() ?: 0f,
                state.value.isLoading
            )
            Spacer(modifier = Modifier.size(48.dp))
            if (!isRecomendationShow && state.value.investmentResult?.isSuccess == false) Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GradientContainer(
                    gradientColors = listOf(GreenPrimary),
                    modifier = Modifier.width(screenWidth.times(0.8f)),
                    onPressed = {
                        isRecomendationShow = true
                    }
                ) {
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Lihat Hasil Rekomendasi",
                            style = MediumBlack.copy(color = Color.White)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GradientContainer(
                    gradientColors = listOf(Color.Transparent),
                    borderColor = Color.Black,
                    onPressed = {
                        navController.navigate(AppRoute.InvestmentCalculator.route) {
                            popUpTo(AppRoute.InvestmentCalculator.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.width(screenWidth.times(0.8f))
                ) {
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Ulangi Pertanyaan",
                            style = MediumBlack
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun CommonSection(
    title: String,
    value: String,
    isLoading: Boolean = false
) {
    val textWidth = (13 * 14.sp.value * 0.6f).dp
    val textHeight = (14.sp.value * 1.5f).dp.minus(2.dp)

    Text(text = title, style = SmallBlack.copy(fontSize = 14.sp))
    Spacer(modifier = Modifier.size(4.dp))
    if (isLoading)
        ShimmerBox(width = textWidth, height = textHeight)
    else
        Text(text = value, style = MediumBlack.copy(fontSize = 14.sp))
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun InvestationDetailSection(
    principalInvestment: Float,
    principalValue: Int = 0,
    interestValue: Int = 0,
    interestInvestment: Float,
    isLoading: Boolean = false
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val textWidth = (13 * 12.sp.value * 0.6f).dp
    val textHeight = (12.sp.value * 1.5f).dp.minus(2.dp)

    val principalInvestmentWidth = principalInvestment.div(100f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp),
    ) {
        Column {
            Text(text = "POKOK INVESTASI", style = SmallBlack.copy(fontSize = 12.sp))
            if (isLoading)
                ShimmerBox(width = textWidth, height = textHeight)
            else
                Text(
                    text = Utils.createRpText(principalValue),
                    style = MediumBlack.copy(fontSize = 12.sp)
                )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "BUNGA INVESTASI", style = SmallBlack.copy(fontSize = 12.sp))
            if (isLoading)
                ShimmerBox(width = textWidth, height = textHeight)
            else
                Text(
                    text = Utils.createRpText(interestValue),
                    style = MediumBlack.copy(fontSize = 12.sp)
                )
        }
    }
    Spacer(modifier = Modifier.size(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GradientContainer(
            cornerRadius = 24,
            gradientColors = listOf(BoldBlue),
            modifier = Modifier.width(screenWidth.times(principalInvestmentWidth) - 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${principalInvestment}%",
                    style = MediumBlack.copy(color = Color.White, fontSize = 14.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        GradientContainer(
            cornerRadius = 24,
            gradientColors = listOf(BoldYellow),
            modifier = Modifier.width(screenWidth.times(1 - principalInvestmentWidth) - 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${interestInvestment}%",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MediumBlack.copy(color = Color.White, fontSize = 14.sp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun InvestationTempoSection(
    currentValue: Int,
    recomendationValue: Int,
    showRecomendationValue: Boolean,
    isSuccess: Boolean = true,
    isLoading: Boolean = false
) {
    Text(text = "TEMPO INVESTASI", style = SmallBlack.copy(fontSize = 14.sp))
    Spacer(modifier = Modifier.size(4.dp))
    if (showRecomendationValue) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "$currentValue TAHUN", style = MediumCrossedRed.copy(
                textDecoration = TextDecoration.LineThrough
            ))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .rotate(-90f),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                tint = GreenPrimary,
            )
            Text(
                text = "$recomendationValue TAHUN",
                style = MediumBlack.copy(fontSize = 14.sp, color = GreenPrimary)
            )
        }
    } else {
        val textWidth = (13 * 14.sp.value * 0.6f).dp
        val textHeight = (14.sp.value * 1.5f).dp.minus(2.dp)

        if (isLoading)
            ShimmerBox(width = textWidth, height = textHeight)
        else
            Text(
                text = "$currentValue TAHUN",
                style = MediumBlack.copy(
                    fontSize = 14.sp,
                    color = if (isSuccess) Color.Black else RedPrimary
                )
            )
    }
    Spacer(modifier = Modifier.size(8.dp))
}
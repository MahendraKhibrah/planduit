package com.pens.planduit.presentation.features.riskProfile.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.RiskProfileBottomSheet
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.riskProfile.viewModel.RiskProfileResultViewModel
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun RiskProfileResultPage(
    navController: NavHostController,
    viewModel: RiskProfileResultViewModel = hiltViewModel<RiskProfileResultViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val textWidth = (13 * 14.sp.value * 0.6f).dp
    val textHeight = (14.sp.value * 1.5f).dp.minus(2.dp)

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.getRiskProfileResult()
    }

    PlanDuitScaffold(
        title = "Kalkulator Profil Resiko",
        hideBackButton = true,
        bottomSheet = {
            CommonBottomSheet(
                data = RiskProfileBottomSheet,
                isOpen = showBottomSheet,
                onDismiss = {
                    showBottomSheet = false
                }
            )
        },
        trailingWidget = {
            val interactionSource =
                remember { MutableInteractionSource() }
//
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
            Spacer(modifier = Modifier.height(32.dp))
            if (state.value.isLoading)
                ShimmerBox(
                    width = textWidth,
                    height = textHeight,
                )
            else
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = BalanceBlack.copy(
                                fontWeight = FontWeight.Light,
                            ).toSpanStyle()
                        ) {
                            append("Profil Resiko kamu adalah :  ")
                        }
                        withStyle(
                            style = BalanceBlack.copy(fontWeight = FontWeight.Medium)
                                .toSpanStyle()
                        ) {
                            append(state.value.data?.type ?: "")
                        }
                    }
                )
            Spacer(modifier = Modifier.height(16.dp))
            if (state.value.isLoading)
                ShimmerBox(width = textWidth, height = textHeight)
            else
                Text(
                    text = state.value.data?.information ?: "",
                    style = SmallBlack,
                    textAlign = TextAlign.Justify
                )
            Spacer(modifier = Modifier.height(24.dp))
            Text("INSTRUMEN INVESTASI", style = SmallBlack.copy(fontSize = 14.sp))
            InvestmentInstrument(
                items = Utils.convertCommasToList(state.value.data?.instrument ?: ""),
                isLoading = state.value.isLoading
            )
            Spacer(modifier = Modifier.height(72.dp))
            GradientContainer(
                gradientColors = listOf(Color.Transparent),
                borderColor = Color.Black,
                onPressed = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(AppRoute.RiskProfile.route)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Ulangi Pertanyaan",
                        style = MediumBlack.copy(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun InvestmentInstrument(
    items: List<String>,
    isLoading: Boolean = false
) {
    if (isLoading) {
        repeat((1..3).count()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Canvas(modifier = Modifier.size(20.dp)) {
                    drawCircle(color = Color.Black, radius = 12f)
                }
                Spacer(modifier = Modifier.width(8.dp))
                ShimmerBox(width = 100.dp, height = 20.dp)
            }
        }
    } else {
        items.forEach {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Canvas(modifier = Modifier.size(20.dp)) {
                    drawCircle(color = Color.Black, radius = 12f)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(it, style = MediumBlack.copy(fontSize = 14.sp))
            }
        }
    }
}

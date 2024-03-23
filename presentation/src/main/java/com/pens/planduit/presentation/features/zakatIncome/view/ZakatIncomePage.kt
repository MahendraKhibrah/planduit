package com.pens.planduit.presentation.features.zakatIncome.view

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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.button.PlanDuitCheckBox
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.IncomeBottomSheet
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.zakatIncome.viewModel.ZIncomeViewModel

@Composable
fun ZakatIncomePage(
    navController: NavController,
    viewModel: ZIncomeViewModel = hiltViewModel<ZIncomeViewModel>()
    ) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val textWidth = Utils.getTextWidth(fontSize = 27f, textLength = 13)
    val textHeight = Utils.getTextHeight(fontSize = 27f)

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.getGoldPrice()
    }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Penghasilan",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = IncomeBottomSheet,
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
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            GradientContainer(
                gradientColors = listOf(PaleBlue, PaleBlue.copy(alpha = 0.8f)),
                showShadow = true,
                cornerRadius = 16
            ) {
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
                        if (state.value.isLoading){
                            ShimmerBox(width = textWidth, height = textHeight)
                        } else {
                            Text(
                                "Rp. " + Utils.addCommasEveryThreeChars(state.value.data.price),
                                style = LeadingGreen.copy(fontSize = 27.sp)
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
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = "Tempo waktu kamu dalam investasi",
                style = MediumBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                PlanDuitCheckBox(text = "Perbulan", onTap = {
                })
                Spacer(modifier = Modifier.size(24.dp))
                PlanDuitCheckBox(text = "Pertahun", onTap = {
                })
            }
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = MediumBlack.copy(fontSize = 12.sp).toSpanStyle()
                    ) {
                        append("Berapa Pendapatan tetap anda per  ")
                    }
                    withStyle(
                        style = MediumBlack.copy(fontSize = 12.sp, color = GreenPrimary)
                            .toSpanStyle()
                    ) {
                        append("bulan")
                    }
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            RpTextField(
                onDone = {},
            )
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = MediumBlack.copy(fontSize = 12.sp).toSpanStyle()
                    ) {
                        append("Berapa Pendapatan lain anda per  ")
                    }
                    withStyle(
                        style = MediumBlack.copy(fontSize = 12.sp, color = GreenPrimary)
                            .toSpanStyle()
                    ) {
                        append("bulan")
                    }
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            RpTextField(
                onDone = {},
            )
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = MediumBlack.copy(fontSize = 12.sp).toSpanStyle()
                    ) {
                        append("Berapa pengeluaran anda per  ")
                    }
                    withStyle(
                        style = MediumBlack.copy(fontSize = 12.sp, color = GreenPrimary)
                            .toSpanStyle()
                    ) {
                        append("bulan")
                    }
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            RpTextField(
                onDone = {},
            )
            Spacer(modifier = Modifier.size(64.dp))
            SubmitButton()
        }
    }
}

@Composable
fun SubmitButton(
    isActive: Boolean = false,
    onPressed: () -> Unit = {}
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
                    text = "Lihat Zakat Penghasilanmu",
                    style = MediumWhite.copy(color = if (isActive) Color.White else DarkGrey)
                )
            }
        }
    }
}
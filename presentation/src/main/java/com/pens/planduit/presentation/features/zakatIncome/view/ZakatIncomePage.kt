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
import androidx.compose.runtime.mutableIntStateOf
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
import com.pens.planduit.common.theme.OffGreen
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatIncome.viewModel.ZIncomeViewModel
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun ZakatIncomePage(
    navController: NavController,
    viewModel: ZIncomeViewModel = hiltViewModel<ZIncomeViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedCheckbox by remember { mutableIntStateOf(0) }
    var questionOneText by remember { mutableStateOf("") }
    var questionTwoText by remember { mutableStateOf("") }
    var questionThreeText by remember { mutableStateOf("") }


    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
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
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            Banner(state = state.value)
            Spacer(modifier = Modifier.size(32.dp))
            FirstSection(
                selectedValue = selectedCheckbox,
                onPressed = {
                    selectedCheckbox = it
                })
            CommonSection(
                question = "Berapa Pendapatan tetap anda per",
                selectedValue = selectedCheckbox,
                onValueChange = {
                    questionOneText = it
                })
            Spacer(modifier = Modifier.size(32.dp))
            CommonSection(
                question = "Berapa Pendapatan lain anda per",
                selectedValue = selectedCheckbox,
                onValueChange = {
                    questionTwoText = it
                })
            Spacer(modifier = Modifier.size(32.dp))
            CommonSection(
                question = "Berapa pengeluaran anda per",
                selectedValue = selectedCheckbox,
                onValueChange = {
                    questionThreeText = it
                })
            Spacer(modifier = Modifier.size(64.dp))
            SubmitButton(
                isActive = selectedCheckbox != -1 &&
                        questionOneText.isNotEmpty() &&
                        questionTwoText.isNotEmpty() &&
                        questionThreeText.isNotEmpty(),
                onPressed = {
                    val jsonString = viewModel.getRequestJsonString(
                        selectedCheckbox,
                        questionOneText,
                        questionTwoText,
                        questionThreeText
                    )
                    navController.navigate(
                        AppRoute.ZakatIncomeResult.withArgs(
                            jsonString,
                            state.value.data.price
                        )
                    )
                }
            )
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
fun FirstSection(
    selectedValue: Int = 0,
    onPressed: (Int) -> Unit
) {
    Text(
        text = "Tempo waktu dalam perhitungan",
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    Row {
        PlanDuitCheckBox(
            text = "Perbulan",
            isChecked = selectedValue == 0,
            onTap = {
                onPressed(0)
            })
        Spacer(modifier = Modifier.size(24.dp))
        PlanDuitCheckBox(text = "Pertahun",
            isChecked = selectedValue == 1,
            onTap = {
                onPressed(1)
            })
    }
}

@Composable
fun CommonSection(
    question: String,
    selectedValue: Int,
    onValueChange: (String) -> Unit
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = MediumBlack.copy(fontSize = 12.sp).toSpanStyle()
            ) {
                append(question)
            }
            withStyle(
                style = MediumBlack.copy(fontSize = 12.sp, color = GreenPrimary)
                    .toSpanStyle()
            ) {
                if (selectedValue == 1)
                    append(" tahun")
                else
                    append(" bulan")
            }
        }
    )
    Spacer(modifier = Modifier.size(16.dp))
    RpTextField(
        onValueChange = onValueChange,
    )
}

@Composable
private fun SubmitButton(
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
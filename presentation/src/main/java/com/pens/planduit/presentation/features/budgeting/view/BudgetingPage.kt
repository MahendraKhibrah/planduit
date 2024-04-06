package com.pens.planduit.presentation.features.budgeting.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitPoint
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.theme.BudgetingBottomSheet
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.budgeting.viewModel.BudgetingViewModel
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun BudgetingPage(
    navController: NavController,
    viewModel : BudgetingViewModel = hiltViewModel<BudgetingViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsStateWithLifecycle()
    val textValue = viewModel.textFieldValue.collectAsStateWithLifecycle()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val textWidth = (13 * 14.sp.value * 0.6f).dp
    val textHeight = (14.sp.value * 1.5f).dp.minus(2.dp)

    PlanDuitScaffold(
        title = "Kalkulator Budgeting",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = BudgetingBottomSheet,
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
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Berapa lama waktu mengumpulkan uang ini",
                style = MediumBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                RpTextField(
                    onValueChange = {
                        viewModel.textFieldValue.value = it
                    },
                    modifier = Modifier.width(screenWidth.times(0.62f))
                )
                Spacer(modifier = Modifier.weight(1f))
                GradientContainer(
                    gradientColors = listOf(if (textValue.value.isNotEmpty() && !state.value.isLoading) GreenPrimary else HalfGrey),
                    modifier = Modifier.size(screenWidth.times(0.23f), 55.dp),
                    cornerRadius = 12,
                    onPressed = {
                        viewModel.getBudgetCalculation()
                    }
                ) {
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
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
            Spacer(modifier = Modifier.size(28.dp))
            Text(
                text = "Berikut hasil hitungan 50/30/20 milik kamu",
                style = MediumBlack.copy(fontSize = 14.sp)
            )
            Row {
                Column {
                    Spacer(modifier = Modifier.size(6.dp))
                    PlanDuitPoint(
                        pointAmount = 3,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = "KEBUTUHAN POKOK (50%)", style = SmallBlack.copy(fontSize = 14.sp))
                    if(state.value.isLoading){
                        ShimmerBox(width = textWidth, height = textHeight)
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = "KEINGINAN (30%)", style = SmallBlack.copy(fontSize = 14.sp))
                        ShimmerBox(width = textWidth, height = textHeight)
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = "TABUNGAN / UTANG (20%)", style = SmallBlack.copy(fontSize = 14.sp))
                        ShimmerBox(width = textWidth, height = textHeight)
                    } else {
                        val data = state.value.data
                        Text(text = "Rp " + if(data != null) Utils.addCommasEveryThreeChars(data.needs) else "-", style = MediumBlack.copy(fontSize = 14.sp))
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = "KEINGINAN (30%)", style = SmallBlack.copy(fontSize = 14.sp))
                        Text(text = "Rp " + if(data != null) Utils.addCommasEveryThreeChars(data.wants) else "-", style = MediumBlack.copy(fontSize = 14.sp))
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = "TABUNGAN / UTANG (20%)", style = SmallBlack.copy(fontSize = 14.sp))
                        Text(text = "Rp " + if(data != null) Utils.addCommasEveryThreeChars(data.savings) else "-", style = MediumBlack.copy(fontSize = 14.sp))
                    }
                }
            }
            Spacer(modifier = Modifier.size(screenWidth.times(0.4f)))
            if(state.value.data != null) GradientContainer(
                gradientColors = listOf(Color.Transparent),
                borderColor = Color.Black,
                onPressed = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.Budgeting.route)
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
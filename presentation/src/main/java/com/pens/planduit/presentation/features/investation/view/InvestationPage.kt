package com.pens.planduit.presentation.features.investation.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
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
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.components.textField.ShortTextField
import com.pens.planduit.common.theme.BudgetingBottomSheet
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.InvestmentBottomSheet
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.presentation.features.investation.viewModel.InvestmentCalculatorViewModel
import com.pens.planduit.presentation.features.investation.widget.InvestationMountFilter
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun InvestationPage(
    navController: NavController,
    viewModel: InvestmentCalculatorViewModel = hiltViewModel<InvestmentCalculatorViewModel>()
) {
    val fieldValueState = viewModel.fieldValueState.collectAsStateWithLifecycle()
    var selectedCheckbox by remember { mutableIntStateOf(-1) }
    var showBottomSheet by remember { mutableStateOf(false) }

    PlanDuitScaffold(
        title = "Kalkulator Investasi",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = InvestmentBottomSheet,
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
            FirstSection(
                onDone = {
                    viewModel.changeFieldFilledState(0, it)
                },
                value = fieldValueState.value[0]
            )
            if (viewModel.showFieldByIndex(0)) SecondSection(
                onDone = {
                    viewModel.changeFieldFilledState(1, it)
                },
                value = fieldValueState.value[1]
            )
            if (viewModel.showFieldByIndex(1)) ThirdSection(
                onDone = {
                    viewModel.changeFieldFilledState(2, it)
                },
                value = fieldValueState.value[2]
            )
            if (viewModel.showFieldByIndex(2)) FourthSection(
                onDone = {
                    viewModel.changeFieldFilledState(3, it)
                },
                value = fieldValueState.value[3]
            )
            if (viewModel.showFieldByIndex(3)) FifthSection(
                onDone = {
                    viewModel.changeFieldFilledState(4, it)
                },
                value = fieldValueState.value[4]
            )
            if (viewModel.showFieldByIndex(4)) SixthSection(
                selectedCheckbox = selectedCheckbox,
                onValueChange = {
                    selectedCheckbox = it
                    viewModel.changeFieldFilledState(5, it.toString())
                }
            )
            if (selectedCheckbox >= 0 && viewModel.showFieldByIndex(4)) LastSection {
                navController.navigate(AppRoute.InvestmentResult.withArgs(selectedCheckbox != 0))
            }
        }

    }
}

@Composable
fun FirstSection(
    onDone: (String) -> Unit,
    value: String = ""
) {
    val focusManager = LocalFocusManager.current

    Text(
        text = "Jumlah uang yang ingin kamu capai",
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    RpTextField(
        onDone = onDone,
        value = value
    )
    Spacer(modifier = Modifier.size(16.dp))
    InvestationMountFilter(selectedMount = value, onTap = {
        onDone(it)
        focusManager.clearFocus()
    })
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun SecondSection(
    onDone: (String) -> Unit,
    value: String = ""
) {
    Text(
        text = "Berapa lama waktu mengumpulkan uang ini",
        style = MediumBlack.copy(fontSize = 12.sp),
        modifier = Modifier.padding(horizontal = 8.dp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    ShortTextField(
        trailingWidget = {
            Text(text = "Tahun", style = SmallBlack.copy(fontSize = 14.sp))
        },
        onDone = onDone,
        value = value
    )
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun ThirdSection(
    onDone: (String) -> Unit,
    value: String = ""
) {
    Text(
        text = "Uang yang kamu miliki sekarang",
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    RpTextField(
        onDone = onDone,
        value = value
    )
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun FourthSection(
    onDone: (String) -> Unit,
    value: String = ""
) {
    Text(
        text = "Jumlah Investasi kamu tiap bulan",
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    RpTextField(
        onDone = onDone,
        value = value
    )
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun FifthSection(
    onDone: (String) -> Unit,
    value: String = ""
) {
    Text(
        text = "Imbal hasil yang kamu harapkan tiap tahun",
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    ShortTextField(
        trailingWidget = {
            Text(text = "%", style = SmallBlack.copy(fontSize = 24.sp))
        },
        onDone = onDone,
        value = value
    )
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun SixthSection(
    selectedCheckbox: Int,
    onValueChange: (Int) -> Unit
) {
    Text(
        text = "Tempo waktu kamu dalam investasi",
        style = MediumBlack.copy(fontSize = 12.sp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    Row {
        PlanDuitCheckBox(text = "Perbulan", onTap = {
            onValueChange(0)
        }, isChecked = selectedCheckbox == 0)
        Spacer(modifier = Modifier.size(24.dp))
        PlanDuitCheckBox(text = "Pertahun", onTap = {
            onValueChange(1)
        }, isChecked = selectedCheckbox == 1)
    }
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun LastSection(
    onPressed: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        GradientContainer(
            onPressed = onPressed,
            gradientColors = listOf(GreenPrimary),
            modifier = Modifier.width(screenWidth.times(0.8f))
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Lihat hasil investasimu",
                    style = MediumBlack.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
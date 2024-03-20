package com.pens.planduit.presentation.features.riskProfile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.option.PlanDuitPagination
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.SmallGrey
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.RiskProfileBottomSheet
import com.pens.planduit.presentation.features.riskProfile.viewModel.QuizViewModel
import com.pens.planduit.presentation.features.riskProfile.widget.QuizView

@Composable
fun RiskProfilePage(
    navController: NavHostController,
    viewModel: QuizViewModel = hiltViewModel<QuizViewModel>()
) {
    val quizState = viewModel.state.collectAsStateWithLifecycle()
    val isCompleteFilled = viewModel.isCompleteFilled.collectAsStateWithLifecycle()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(true){
        viewModel.getQuestions()
    }

    PlanDuitScaffold(
        title = "Kalkulator Profil Resiko",
        onBackPressed = {
            navController.popBackStack()
        },
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
                remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
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
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SmallGrey.copy(
                            color = BlackPrimary,
                            fontSize = 14.sp
                        ).toSpanStyle()
                    ) {
                        append("Pertanyaan ${quizState.value.data?.size}/7 : ")
                    }
                    withStyle(
                        style = LeadingGreen.copy(fontSize = 14.sp)
                            .toSpanStyle()
                    ) {
                        append("Tujuan Investasi")
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                color = Color(0xFFEDEDED)
            )
            Spacer(modifier = Modifier.height(28.dp))
            if (quizState.value.isLoading.not()){
                key(quizState.value.selectedData.number){
                    QuizView(
                        question = quizState.value.selectedData.question ?: "",
                        options = quizState.value.selectedData.choices.map { it.label } ?: emptyList(),
                        onChanged = {
                            viewModel.changeChoice(it)
                        },
                        initialValue = quizState.value.selectedData.selectedChoice ?: -1
                    )
                }
            } else {
                ShimmerBox(width = screenWidth, height = 430.dp, cornerRadius = 16)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.width(screenWidth)

            ) {
                Spacer(modifier = Modifier.weight(1f))
                PlanDuitPagination(pageCount = 7, hidePageButton = true ,onChanged = {
                    viewModel.changePage(it)
                })
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            SubmitButton(
                isActive = isCompleteFilled.value && quizState.value.isLoading.not()
            )
        }
    }
}

@Composable
fun SubmitButton(
    isActive : Boolean = false,
    onPressed : () -> Unit = {}
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        GradientContainer(
            gradientColors = listOf(if (isActive) GreenPrimary else HalfGrey),
            modifier = Modifier.size(screenWidth.times(0.8f), 45.dp),
            cornerRadius = 12,
            onPressed = {
                if (isActive)
                    onPressed()
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
                    style = MediumWhite.copy(color = if (isActive) Color.White else DarkGrey)
                )
            }
        }
    }
}
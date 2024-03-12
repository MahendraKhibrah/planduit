package com.pens.planduit.presentation.features.riskProfile.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.option.PlanDuitPagination
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.SmallGrey
import com.pens.planduit.common.R
import com.pens.planduit.presentation.features.riskProfile.QuizViewModel
import com.pens.planduit.presentation.features.riskProfile.widget.QuizView

@Composable
fun RiskProfilePage(
    navController: NavHostController,
    viewModel: QuizViewModel = hiltViewModel<QuizViewModel>()
) {
    val quizState = viewModel.state.collectAsStateWithLifecycle()

    PlanDuitScaffold(
        title = "Kalkulator Profil Resiko",
        onBackPressed = {
            navController.popBackStack()
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
                        onClick = {},
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
                        append("Pertanyaan ${quizState.value.number}/7 : ")
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
            key(quizState.value.number, quizState.value.selectedChoice) {
                QuizView(
                    question = quizState.value.question,
                    options = quizState.value.choices.map {
                        it.label
                    },
                    onChanged = {
                        viewModel.changeChoice(it)
                    },
                    initialValue = quizState.value.selectedChoice ?: -1
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            PlanDuitPagination(pageCount = 7, onChanged = {
                viewModel.changePage(it)
            })
        }
    }
}

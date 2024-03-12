package com.pens.planduit.presentation.features.riskProfile.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.option.CheckBoxList
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.HalfGrey

@Composable
fun QuizView (
    question : String,
    options : List<String>,
    onChanged : (Int) -> Unit,
    initialValue : Int = -1
){
    GradientContainer(
        isGradientVertical = true,
        gradientColors = listOf(
            Color(0xFFE4FED8).copy(alpha = 0.6f),
            Color(0xFFD2FFEF).copy(alpha = 0.6f)
        ),
        cornerRadius = 16,
        borderColor = HalfGrey
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .height(380.dp)
        ) {
            Text(
                question,
                style = BalanceBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            CheckBoxList(
                items = options,
                onChanged = onChanged,
                initialSelected = initialValue
            )
        }
    }
}
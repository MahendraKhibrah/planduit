package com.pens.planduit.presentation.features.investation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.MediumBlack

@Composable
fun InvestationMountFilter(
    selectedMount: String,
    onTap: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InvestationMountItem(isSelected = selectedMount == "10,000,000", text = "10 juta", onTap = { onTap("10,000,000") })
        Spacer(modifier = Modifier.size(6.dp))
        InvestationMountItem(isSelected = selectedMount == "50,000,000", text = "50 juta", onTap = { onTap("50,000,000") })
        Spacer(modifier = Modifier.size(6.dp))
        InvestationMountItem(isSelected = selectedMount == "100,000,000", text = "100 juta", onTap = { onTap("100,000,000") })
        Spacer(modifier = Modifier.size(6.dp))
        InvestationMountItem(isSelected = selectedMount == "1,000,000,000", text = "1 Miliyar", onTap = { onTap("1,000,000,000") })
    }
}

@Composable
fun InvestationMountItem(
    isSelected: Boolean,
    text: String,
    onTap: () -> Unit
) {
    GradientContainer(
        gradientColors = listOf(if (isSelected) GreenPrimary else Color.Transparent),
        borderColor = if (isSelected) Color.Transparent else GreenPrimary,
        onPressed = { onTap() }
    ) {
        Text(
            text = text,
            style = MediumBlack.copy(
                color = if (isSelected) Color.White else GreenPrimary,
                fontSize = 9.sp,
            ),
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 7.dp)
        )
    }
}
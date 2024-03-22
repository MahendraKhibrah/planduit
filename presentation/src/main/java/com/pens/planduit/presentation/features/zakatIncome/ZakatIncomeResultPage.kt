package com.pens.planduit.presentation.features.zakatIncome

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.BudgetingBottomSheet
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SmallBlack

@Composable
fun ZakatIncomeResultPage(
    navController: NavController
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Penghasilan",
        onBackPressed = {

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
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            GradientContainer(
                gradientColors = listOf(PaleBlue.copy(alpha = 0.8f), PaleBlue),
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
                        modifier = Modifier.width(screenWidth.times(0.5f))
                    ) {
                        Text(
                            "Kamu wajib membayar zakat penghasilan!",
                            style = MediumBlack.copy(color = BlackPrimary)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = null,
                        modifier = Modifier.height(120.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "PENDAPATANMU PER BULAN", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rp 1.000.000", style = MediumBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "PENDAPATAN LAINMU PER BULAN", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rp 1.000.000", style = MediumBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "PENGELUARANMU PER BULAN", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rp 1.000.000", style = MediumBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "ZAKAT YANG HARUS KAMU KELUARKAN",
                style = SmallBlack.copy(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_zakat),
                    contentDescription = null,
                    modifier = Modifier.size(21.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Rp 1.000.000", style = MediumBlack.copy(fontSize = 14.sp))
            }
            Spacer(modifier = Modifier.height(64.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GradientContainer(
                    gradientColors = listOf(Color.Transparent),
                    borderColor = Color.Black,
                    onPressed = {
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
        }

    }
}
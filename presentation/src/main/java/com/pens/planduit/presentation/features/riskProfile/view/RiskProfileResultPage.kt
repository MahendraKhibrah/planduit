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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.RiskProfileBottomSheet
import com.pens.planduit.common.theme.SmallBlack

@Preview(showBackground = true)
@Composable
fun RiskProfileResultPage() {
    var showBottomSheet by remember { mutableStateOf(false) }

    PlanDuitScaffold(
        title = "Kalkulator Profil Resiko",
        onBackPressed = {
//            navController.popBackStack()
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
                        append("Moderat")
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Investor tipe Moderat. Menyeimbangkan antara risiko dengan imbal hasil agar mencapai keuntungan yang optimal secara berkala namun tetap berhati-hati saat menentukan instrumen investatinya.", style = SmallBlack)
            Spacer(modifier = Modifier.height(24.dp))
            Text("INSTRUMEN INVESTASI", style = SmallBlack.copy(fontSize = 14.sp))
            InvestmentInstrument(
                listOf("Reksa dana", "Deposito", "Crypto")
            )
            Spacer(modifier = Modifier.height(72.dp))
            GradientContainer(
                gradientColors = listOf(Color.Transparent),
                borderColor = Color.Black,
                onPressed = {
//                    navController.popBackStack()
//                    navController.popBackStack()
//                    navController.navigate(AppRoute.Budgeting.route)
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
    items : List<String>
){
    items.forEach {
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Canvas(modifier = Modifier.size(20.dp)) {
                drawCircle(color = Color.Black, radius = 12f)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(it, style = MediumBlack.copy(fontSize = 14.sp))
        }
    }
}

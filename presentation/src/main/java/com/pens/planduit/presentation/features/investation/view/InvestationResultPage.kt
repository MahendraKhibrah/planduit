package com.pens.planduit.presentation.features.investation.view

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.theme.BoldBlue
import com.pens.planduit.common.theme.BoldRed
import com.pens.planduit.common.theme.BoldYellow
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumCrossedRed
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.RedPrimary
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun InvestationResultPage(
    navController: NavController,
    isNotAchieved: Boolean = false
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    PlanDuitScaffold(
        title = "Kalkulator Investasi",
        onBackPressed = {
            navController.popBackStack()
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
                        onClick = {},
                        interactionSource = interactionSource,
                        indication = null
                    )
            )
        }
    ) {
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            GradientContainer(
                gradientColors = listOf(if (isNotAchieved) RedPrimary else PaleBlue),
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
                        modifier = Modifier.width(screenWidth.times(0.6f))
                    ) {
                        Text(
                            if (isNotAchieved) "Yah, strategimu belum cocok untuk mencapai target investasimu"
                            else "Yeay! strategimu cocok untuk mencapai target investasimu",
                            style = MediumBlack.copy(fontSize = 14.sp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = null,
                        modifier = Modifier.height(100.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(32.dp))
            CommonSection("TARGET YANG INGIN KAMU CAPAI", "RP. 10.000.000")
            CommonSection("UANG YANG ADA SAAT INI", "RP. 1.000.000")
            CommonSection("JUMLAH INVESTASI KAMU", "RP. 84.000")
            CommonSection("RETURN YANG DIHARAPKAN/TAHUN", "10%")
            if (isNotAchieved)
                InvestationTempoSection("10 TAHUN", "12 TAHUN")
            else
                CommonSection("TEMPO INVESTASI", "10 TAHUN")

            Text(text = "HASIL RENCANA KAMU", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_money_bag),
                    contentDescription = null,
                    modifier = Modifier.size(21.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Rp 20.507.256", style = MediumBlack.copy(fontSize = 14.sp))
            }
            Spacer(modifier = Modifier.size(24.dp))
            InvestationDetailSection(0.622f)
            Spacer(modifier = Modifier.size(48.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GradientContainer(
                    gradientColors = listOf(GreenPrimary),
                    modifier = Modifier.width(screenWidth.times(0.8f))
                ) {
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Lihat Hasil Rekomendasi",
                            style = MediumBlack.copy(color = Color.White)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GradientContainer(
                    gradientColors = listOf(Color.Transparent),
                    borderColor = Color.Black,
                    onPressed = {
                        navController.navigate(AppRoute.InvestmentCalculator.route){
                            popUpTo(AppRoute.InvestmentCalculator.route){
                                inclusive = true
                            }
                        }
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
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun CommonSection(
    title: String,
    value: String
) {
    Text(text = title, style = SmallBlack.copy(fontSize = 14.sp))
    Spacer(modifier = Modifier.size(4.dp))
    Text(text = value, style = MediumBlack.copy(fontSize = 14.sp))
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun InvestationDetailSection(
    principalInvestment: Float
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp),
    ) {
        Column {
            Text(text = "POKOK INVESTASI", style = SmallBlack.copy(fontSize = 12.sp))
            Text(text = "Rp 20.507.256", style = MediumBlack.copy(fontSize = 12.sp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "BUNGA INVESTASI", style = SmallBlack.copy(fontSize = 12.sp))
            Text(text = "Rp 20.507.256", style = MediumBlack.copy(fontSize = 12.sp))
        }
    }
    Spacer(modifier = Modifier.size(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GradientContainer(
            cornerRadius = 24,
            gradientColors = listOf(BoldBlue),
            modifier = Modifier.width(screenWidth.times(principalInvestment) - 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "62.2%",
                    style = MediumBlack.copy(color = Color.White, fontSize = 14.sp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        GradientContainer(
            cornerRadius = 24,
            gradientColors = listOf(BoldYellow),
            modifier = Modifier.width(screenWidth.times(1 - principalInvestment) - 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "38.4%",
                    style = MediumBlack.copy(color = Color.White, fontSize = 14.sp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun InvestationTempoSection(
    currentValue: String,
    recomendationValue: String
) {
    Text(text = "TEMPO INVESTASI", style = SmallBlack.copy(fontSize = 14.sp))
    Spacer(modifier = Modifier.size(4.dp))
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = currentValue, style = MediumCrossedRed)
        Icon(
            modifier = Modifier
                .size(24.dp)
                .rotate(-90f),
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "",
            tint = GreenPrimary,
        )
        Text(text = recomendationValue, style = MediumBlack.copy(fontSize = 14.sp, color = GreenPrimary))
    }
    Spacer(modifier = Modifier.size(8.dp))
}
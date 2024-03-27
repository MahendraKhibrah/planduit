package com.pens.planduit.presentation.features.zakatAgriculture.view

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.option.CheckBoxList
import com.pens.planduit.common.components.textField.RpTextField
import com.pens.planduit.common.theme.AgricultureBottomSheet
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState

@Preview(showBackground = true)
@Composable
fun ZakatAgriculturePage(
    navController : NavController
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LaunchedEffect(true) {
    }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Pertanian",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = AgricultureBottomSheet,
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
            Banner(state = GoldPriceState())
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = "Berapa hasil pertanian kamu saat panen (dalam kilogram)",
                style = MediumBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            RpTextField(hideLeading = true)
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = "Apa jenis pengairan yang kamu gunakan?",
                style = MediumBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(18.dp))
            CheckBoxList(
               items = listOf<String>(
                   "Menggunakan hujan / mata air",
                   "Menggunakan irigasi"
               )
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Berapa harga beras di daerah kamu",
                style = MediumBlack.copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            RpTextField()
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "*jika tidak diisi akan menggunakan harga rata-rata beras di Indonesia \n" +
                        "(data diambil dari badanpangan.go.id)",
                style = BalanceBlack.copy(fontSize = 11.sp, color = GreenPrimary)
            )
            Spacer(modifier = Modifier.size(64.dp))
            SubmitButton()
        }
    }
}

@Composable
private fun Banner(
    state: GoldPriceState
) {
    GradientContainer(
        gradientColors = listOf(PaleBlue, PaleBlue.copy(alpha = 0.8f)),
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
                    "Harga Beras Hari ini",
                    style = SmallBlack.copy(fontSize = 15.sp, color = Color(0xFF606060))
                )
                if (state.isLoading) {
                    ShimmerBox(width = textWidth, height = textHeight)
                } else {
                    Text(
                        "Rp. " + Utils.addCommasEveryThreeChars(state.data.price),
                        style = LeadingGreen.copy(fontSize = 25.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.money_palm),
                contentDescription = null,
                modifier = Modifier.sizeIn(minWidth = 80.dp, minHeight = 80.dp)
            )
        }

    }
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
                    text = "Lihat Zakat Pertanianmu",
                    style = MediumWhite.copy(color = if (isActive) Color.White else DarkGrey)
                )
            }
        }
    }
}
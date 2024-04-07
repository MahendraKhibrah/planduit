package com.pens.planduit.presentation.features.zakatIncome.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.container.ZakatResultBanner
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.BudgetingBottomSheet
import com.pens.planduit.common.theme.IncomeBottomSheet
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.OffGreen
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.RedPrimary
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatIncome.viewModel.ZIncomeResultViewModel
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun ZakatIncomeResultPage(
    navController: NavController,
    request: IncomeZakatRequest,
    goldPrice: String,
    viewModel: ZIncomeResultViewModel = hiltViewModel<ZIncomeResultViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getZakatIncome(request)
    }

    PlanDuitScaffold(
        title = "Kalkulator Zakat Penghasilan",
        onBackPressed = {
            navController.popBackStack()
        },
        bottomSheet = {
            CommonBottomSheet(
                data = IncomeBottomSheet,
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
            Banner(price = goldPrice)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "PENDAPATANMU PER BULAN", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(price = request.income.toLong())
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "PENDAPATAN LAINMU PER BULAN", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(price = request.anotherIncome)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "PENGELUARANMU PER BULAN", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(price = request.expenditure)
            if (state.value.data.status) {
                Spacer(modifier = Modifier.height(24.dp))
                ResultSection(isLoading = state.value.isLoading, price = state.value.data.zakat)
            }
            Spacer(modifier = Modifier.height(64.dp))
            ZakatResultBanner(isLoading = state.value.isLoading, isSuccess = state.value.data.status, title = "penghasilan")
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GradientContainer(
                    gradientColors = listOf(Color.Transparent),
                    borderColor = Color.Black,
                    onPressed = {
                        navController.popBackStack()
                        navController.popBackStack()
                        navController.navigate(AppRoute.ZakatIncome.route)
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


@Composable
fun Banner(
    price: String
) {
    GradientContainer(
        gradientColors = listOf(PaleBlue, OffGreen),
        showShadow = true,
        cornerRadius = 16,
    ) {

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

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
                    "Harga Emas Hari ini",
                    style = SmallBlack.copy(fontSize = 15.sp, color = Color(0xFF606060))
                )

                Text(
                    "Rp. $price",
                    style = LeadingGreen.copy(fontSize = 25.sp)
                )


            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.gold_price),
                contentDescription = null,
                modifier = Modifier.sizeIn(minWidth = 80.dp, minHeight = 80.dp)
            )
        }

    }
}

@Composable
fun CommonPrice(
    isLoading: Boolean = false,
    customTitle : String? = null,
    price: Number
) {
    val textWidth = Utils.getTextWidth(14f, 13)
    val textHeight = Utils.getTextHeight(14f)

    val finalPrice = Utils.addCommasEveryThreeChars(price)

    if (isLoading)
        ShimmerBox(width = textWidth, height = textHeight)
    else
        Text(text = customTitle ?: "Rp $finalPrice", style = MediumBlack.copy(fontSize = 14.sp))
}

@Composable
fun ResultSection(
    isLoading: Boolean = false,
    price: Number
) {
    if (isLoading)
        ShimmerBox(width = Utils.getTextWidth(14f, 27), height = Utils.getTextHeight(14f))
    else
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
        CommonPrice(price = price, isLoading = isLoading)
    }
}
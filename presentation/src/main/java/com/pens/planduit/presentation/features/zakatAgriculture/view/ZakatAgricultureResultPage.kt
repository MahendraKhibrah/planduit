package com.pens.planduit.presentation.features.zakatAgriculture.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.button.CommonOutlinedButton
import com.pens.planduit.common.components.container.CommonBottomSheet
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.container.ZakatResultBanner
import com.pens.planduit.common.theme.AgricultureBottomSheet
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.OffGreen
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.zakatAgriculture.viewModel.ZAgricultureResultViewModel
import com.pens.planduit.presentation.features.zakatAgriculture.viewModel.ZAgricultureViewModel
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatIncome.view.CommonPrice
import com.pens.planduit.presentation.features.zakatIncome.view.ResultSection
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun ZakatAgricultureResultPage(
    navController: NavController,
    request: String,
    ricePrice: Int,
    viewModel: ZAgricultureResultViewModel = hiltViewModel<ZAgricultureResultViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getAgricultureZakat(request)
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
            Banner(price = ricePrice)
            Spacer(modifier = Modifier.size(32.dp))
            Text(text = "HASIL PERTANIAN KAMU", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(
                price = viewModel.getRequestModel(request).totalHarvest,
                isLoading = state.value.isLoading,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "JENIS PERAIRAN KAMU", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(
                price = 0,
                isLoading = state.value.isLoading,
                customTitle = viewModel.getWateredValue(request)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "HARGA BERAS DI DAERAH KAMU", style = SmallBlack.copy(fontSize = 14.sp))
            Spacer(modifier = Modifier.height(8.dp))
            CommonPrice(
                price = viewModel.getRequestModel(request).grainPrice,
                isLoading = state.value.isLoading
            )
            if (state.value.data.status) {
                Spacer(modifier = Modifier.height(24.dp))
                ResultSection(isLoading = state.value.isLoading, price = state.value.data.zakat)
            }
            Spacer(modifier = Modifier.height(100.dp))
            ZakatResultBanner(
                isLoading = state.value.isLoading,
                isSuccess = state.value.data.status,
                title = "Pertanian"
            )
            Spacer(modifier = Modifier.height(24.dp))
            CommonOutlinedButton(onPressed = {
                navController.popBackStack()
                navController.popBackStack()
                navController.navigate(AppRoute.ZakatAgriculture.route)
            }, title = "Ulangi Pertanyaan")
        }
    }
}

@Composable
private fun Banner(
    price: Int
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
                    "Harga Beras Hari ini",
                    style = SmallBlack.copy(fontSize = 15.sp, color = Color(0xFF606060))
                )
                Text(
                    "Rp. " + Utils.addCommasEveryThreeChars(price.toLong()),
                    style = LeadingGreen.copy(fontSize = 25.sp)
                )
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
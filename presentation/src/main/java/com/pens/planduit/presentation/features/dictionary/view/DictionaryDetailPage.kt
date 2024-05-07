package com.pens.planduit.presentation.features.dictionary.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BoldBalanceBlack
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.dictionary.viewModel.DictionaryDetailViewModel
import com.pens.planduit.presentation.features.dictionary.viewModel.DictionaryViewModel

@Composable
fun DictionaryDetailPage(
    navController: NavHostController,
    id : Int,
    viewModel: DictionaryDetailViewModel = hiltViewModel<DictionaryDetailViewModel>()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.getDictionaryDetail(id)
    }

    PlanDuitScaffold(
        title = "Detail Kamus",
        hideBackButton = false,
        onBackPressed = {
            navController.popBackStack()
        },
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        Column {
            Spacer(modifier = Modifier.height(18.dp))
            Box(
                modifier = Modifier
                    .width(screenWidth)
                    .clip(shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                    .background(color = GreenPrimary)
                    .padding(vertical = 30.dp, horizontal = 16.dp)
            ) {
                if (state.value.isLoading){
                    ShimmerBox(
                        width = screenWidth.times(0.75f),
                        height = Utils.getTextHeight(22f)
                    )
                } else {
                Text(
                    state.value.data?.title ?: "",
                    style = BoldBalanceBlack.copy(fontSize = 22.sp)
                )
                }
            }
            Box(
                modifier = Modifier
                    .width(screenWidth)
                    .height(screenHeight.times(0.5f))
                    .offset(y = (-20).dp)
                    .border(
                        width = 2.dp,
                        color = GreenPrimary,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(end = 16.dp, start = 16.dp, top = 35.dp, bottom = 16.dp)
            ) {
                if (state.value.isLoading){
                    ShimmerBox(
                        width = screenWidth.times(0.75f),
                        height = Utils.getTextHeight(14f)
                    )
                }
                Text(state.value.data?.description ?: "",
                    style = BalanceBlack)
            }
        }
    }
}
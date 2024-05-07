package com.pens.planduit.presentation.features.dictionary.view

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.components.textField.SearchTextField
import com.pens.planduit.common.theme.BoldBalanceBlack
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.dictionary.viewModel.DictionaryViewModel
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun DictionaryPage(
    viewModel: DictionaryViewModel = hiltViewModel<DictionaryViewModel>(),
    navController: NavHostController
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scrollState: ScrollState = rememberScrollState()
    var selectedKey: String by rememberSaveable {
        mutableStateOf("A")
    }
    var searchKey: String by rememberSaveable {
        mutableStateOf("")
    }
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(selectedKey, searchKey) {
        viewModel.getDictionary(
            group = selectedKey,
            search = searchKey
        )
    }

    PlanDuitScaffold(
        hideBackButton = true,
        title = "Kamus Investasi",
        stickyHeader = {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                SearchTextField(
                    placeHolder = "Cari Kata",
                    value = searchKey,
                    onSearch = {
                        searchKey = it
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .horizontalScroll(scrollState)
            ) {
                ('A'..'Z').forEach {
                    GradientContainer(
                        gradientColors = listOf(
                            if (
                                it.toString() == selectedKey &&
                                searchKey.isEmpty()) GreenPrimary
                            else Color.Transparent
                        ),
                        modifier = Modifier.padding(end = 8.dp),
                        onPressed = {
                            it.toString().also {
                                selectedKey = it
                                searchKey = ""
                            }
                        }
                    ) {
                        Text(
                            text = it.toString(),
                            style = BoldBalanceBlack
                                .copy(
                                    color = if (
                                        it.toString() == selectedKey &&
                                        searchKey.isEmpty())
                                        Color.White
                                    else
                                        Color.Black
                                )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(
                color = HalfGrey,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            if (state.value.isLoading) {
                (0..10).forEach { _ ->
                    Spacer(modifier = Modifier.height(16.dp))
                    ShimmerBox(
                        width = screenWidth,
                        height = Utils.getTextHeight(fontSize = 16f.plus(32))
                    )
                }
            } else if (state.value.data.isNotEmpty()) {
                state.value.data.forEach {
                    Spacer(modifier = Modifier.height(16.dp))
                    GradientContainer(
                        gradientColors = listOf(GreenPrimary),
                        onPressed = {
                            navController.navigate(AppRoute.DictionaryDetail.withArgs(it.id))
                        }
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .width(screenWidth.times(0.75f))
                                    .padding(vertical = 16.dp)
                            ) {
                                Text(
                                    text = it.title,
                                    style = BoldBalanceBlack
                                        .copy(fontSize = 12.sp)
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons
                                    .Default.KeyboardArrowRight,
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(45.dp)
                                    .padding(top = 3.dp, start = 8.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
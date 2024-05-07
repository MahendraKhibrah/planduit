package com.pens.planduit.presentation.features.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BalanceGrey
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.BoldBalanceBlack
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.article.widget.ArticleCard
import com.pens.planduit.presentation.features.main.state.HomeArticleState
import com.pens.planduit.presentation.features.main.viewModel.HomeViewModel
import com.pens.planduit.presentation.features.main.widget.MenuItem
import com.pens.planduit.presentation.features.main.widget.RatingDialog
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun HomePage(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    onArticleClick: () -> Unit = {}
) {
    var showRatingDialog by remember { mutableStateOf(false) }

    val state = homeViewModel.state.collectAsStateWithLifecycle()
    val articleState = homeViewModel.articleState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        showRatingDialog = homeViewModel.getRatingStatus()
        homeViewModel.getArticles()
    }

    if (state.value.data == true) {
        homeViewModel.markAsRead()
        showRatingDialog = false
    }

    PlanDuitScaffold(
        showAppBar = false,
        hideContentPadding = true,
        bottomSheet = {
            if (showRatingDialog) {
                RatingDialog(
                    onDismiss = {
                        homeViewModel.markAsRead()
                        showRatingDialog = false
                    },
                    onPressed = {
                        homeViewModel.postRating(it)
                    },
                    isLoading = state.value.isLoading
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBanner()
            MenuContainer(navController, articleState){
              onArticleClick()
            }
        }

    }
}

@Composable
internal fun TopBanner() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .width(screenWidth)
            .height(165.dp)
            .shadow(15.dp)
            .background(GreenPrimary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 42.dp)
        ) {
            Column {
                Text("Hitung Investasi & \nKelola Keuangan Anda!", style = BalanceBlack)
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = BoldBalanceBlack.copy(
                                color = BlackPrimary,
                            ).toSpanStyle()
                        ) {
                            append("Gunakan ")
                        }
                        withStyle(
                            style = BoldBalanceBlack.copy(color = Color.White)
                                .toSpanStyle()
                        ) {
                            append("Planduit")
                        }
                        withStyle(
                            style = BoldBalanceBlack.copy(
                                color = BlackPrimary,
                            ).toSpanStyle()
                        ) {
                            append("\nSekarang!")
                        }
                    }
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
}

@Composable
internal fun MenuContainer(
    navController: NavHostController,
    state : State<HomeArticleState>,
    onArticleClick: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 145.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GradientContainer(gradientColors = listOf(Color.White, Color.White), showShadow = true) {
            Column(
                modifier = Modifier
                    .width(screenWidth.times(0.85f))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    MenuCategory(
                        title = "Investasi",
                        isActivated = selectedIndex == 0,
                        onPressed = {
                            selectedIndex = 0
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                    MenuCategory(title = "Zakat", isActivated = selectedIndex == 1, onPressed = {
                        selectedIndex = 1
                    })
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (selectedIndex == 0) Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    MenuItem(
                        title = "Profil Resiko",
                        imageId = R.drawable.ic_risk_profile,
                        onPressed = {
                            navController.navigate(AppRoute.RiskProfile.route)
                        })
                    Spacer(modifier = Modifier.weight(1f))
                    MenuItem(title = "Investasi", imageId = R.drawable.ic_investation, onPressed = {
                        navController.navigate(AppRoute.InvestmentCalculator.route)
                    })
                    Spacer(modifier = Modifier.weight(1f))
                    MenuItem(
                        title = "Budgeting",
                        imageId = R.drawable.ic_budgeting,
                        onPressed = {
                            navController.navigate(AppRoute.Budgeting.route)
                        }
                    )
                }
                else Column {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        MenuItem(
                            title = "Perdagangan",
                            imageId = R.drawable.ic_trading,
                            onPressed = {
                                navController.navigate(AppRoute.ZakatTrade.route)
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        MenuItem(
                            title = "Penghasilan",
                            imageId = R.drawable.ic_income,
                            onPressed = {
                                navController.navigate(AppRoute.ZakatIncome.route)
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        MenuItem(title = "Emas", imageId = R.drawable.ic_gold, onPressed = {
                            navController.navigate(AppRoute.ZakatGold.route)
                        })
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        MenuItem(
                            title = "Pertanian",
                            imageId = R.drawable.ic_agriculture,
                            onPressed = {
                                navController.navigate(AppRoute.ZakatAgriculture.route)
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        MenuItem(title = "Tabungan", imageId = R.drawable.ic_saving, onPressed = {
                            navController.navigate(AppRoute.ZakatSaving.route)
                        })
                        Spacer(modifier = Modifier.weight(1f))
                        Box(modifier = Modifier.width(83.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Content(state,navController ,onArticleClick)
    }
}

@Composable
internal fun Content(
    state : State<HomeArticleState>,
    navController: NavHostController,
    onPressed: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Artikel", style = LeadingGreen.copy(fontSize = 16.sp))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Lihat Semua",
                style = BalanceGrey.copy(fontSize = 12.sp),
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onPressed()
                    }
                ))
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (state.value.isLoading){
            (0..2).forEach { _ ->
                ArticleCard(title = "", description = "", date = "" , onLoading = true, thumbnailUrl = "")
            }
        } else {
            if (state.value.data.isNotEmpty()){
                state.value.data.forEach {
                    ArticleCard(
                        title = it.title,
                        description = it.shortDescription,
                        date = Utils.formatDate(it.createdAt),
                        hideDivider = state.value.data.indexOf(it) == state.value.data.size - 1,
                        thumbnailUrl = it.thumbnail,
                        onTap = {
                            onPressed()
                            navController.navigate(AppRoute.ArticleDetail.withArgs(it.slug))
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
internal fun MenuCategory(
    title: String,
    onPressed: () -> Unit = {},
    isActivated: Boolean = false,
) {
    GradientContainer(
        gradientColors = if (isActivated) listOf(
            GreenPrimary.copy(alpha = 0.20f),
            GreenPrimary.copy(alpha = 0.20f)
        ) else listOf(
            HalfGrey.copy(alpha = 0.20f),
            HalfGrey.copy(alpha = 0.20f)
        ),
        cornerRadius = 18,
        onPressed = onPressed
    ) {
        Box(
            modifier = Modifier.width(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MediumBlack.copy(
                    fontSize = 10.sp,
                    color = if (isActivated) GreenPrimary else BlackPrimary
                )
            )
        }
    }
}
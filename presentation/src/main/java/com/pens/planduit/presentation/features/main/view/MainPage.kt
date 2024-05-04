package com.pens.planduit.presentation.features.main.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.MediumGrey
import com.pens.planduit.presentation.features.article.view.ArticlePage
import com.pens.planduit.presentation.features.main.widget.BottomNavItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPage(
    navController: NavHostController
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val pagerState = rememberPagerState {
        3
    }
    var activeIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(activeIndex) {
        pagerState.scrollToPage(activeIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        activeIndex = pagerState.currentPage
    }

    Box(
        modifier = Modifier
            .width(screenWidth)
            .height(screenHeight)
    ) {

        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            when (page) {
                0 -> HomePage(navController = navController)
                1 -> ArticlePage(navController = navController)
//                2 -> HomePage(navController = navController)
            }
        }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .width(screenWidth)
                    .height(100.dp)
                    .shadow(elevation = 10.dp)
            ) {
                BottomNavItem(
                    onClick = { activeIndex = 0 },
                    isActive = activeIndex == 0,
                    icons = listOf(R.drawable.ic_calcultator, R.drawable.ic_calculator_active),
                    title = "Kalculator"
                )
                BottomNavItem(
                    onClick = { activeIndex = 1 },
                    isActive = activeIndex == 1,
                    icons = listOf(R.drawable.ic_article, R.drawable.ic_article_active),
                    title = "Artikel"
                )
                BottomNavItem(
                    onClick = { activeIndex = 2 },
                    isActive = activeIndex == 2,
                    icons = listOf(R.drawable.ic_dictionary, R.drawable.ic_dictionary_active),
                    title = "Kamus"
                )

            }
        }

    }
}
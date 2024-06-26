package com.pens.planduit.presentation.features.main.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pens.planduit.common.R
import com.pens.planduit.presentation.features.article.view.ArticlePage
import com.pens.planduit.presentation.features.dictionary.view.DictionaryPage
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
    var activeIndex by rememberSaveable { mutableIntStateOf(0) }

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
                0 -> HomePage(
                    navController = navController,
                    onArticleClick = {
                        activeIndex = 1
                    }
                )
                1 -> ArticlePage(navController = navController)
                2 -> DictionaryPage(navController = navController)
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
                    title = "Kalkulator"
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
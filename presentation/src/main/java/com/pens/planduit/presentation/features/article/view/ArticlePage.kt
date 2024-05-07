package com.pens.planduit.presentation.features.article.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.textField.SearchTextField
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.article.viewModel.ArticlesViewModel
import com.pens.planduit.presentation.features.article.widget.ArticleCard
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun ArticlePage(
    navController: NavHostController,
    viewModel : ArticlesViewModel = hiltViewModel<ArticlesViewModel>()
) {
    val state = viewModel.articleState.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.getArticles()
    }

    PlanDuitScaffold(
        title = "Artikel",
        hideBackButton = true,
        onBackPressed = {
            navController.popBackStack()
        },
        stickyHeader = {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                SearchTextField(
                    placeHolder = "Cari artikel",

                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    ){
            Column {
                if (state.value.isLoading) {
                    (0..10).forEach {
                        ArticleCard(
                            thumbnailUrl = "",
                            title = "",
                            description = "",
                            date = "",
                            onLoading = true,
                            hideDivider = it == 10
                        )
                    }
                } else {
                    state.value.data.forEachIndexed { index, article ->
                        ArticleCard(
                            thumbnailUrl = article.thumbnail,
                            title = article.title,
                            description = article.shortDescription,
                            date = Utils.formatDate(article.createdAt),
                            hideDivider = index == state.value.data.size - 1,
                            onTap = {
                                navController.navigate(AppRoute.ArticleDetail.withArgs(article.slug))
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }

    }
}
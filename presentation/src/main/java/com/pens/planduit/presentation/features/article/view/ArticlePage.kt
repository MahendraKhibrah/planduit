package com.pens.planduit.presentation.features.article.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.textField.SearchTextField
import com.pens.planduit.presentation.features.article.widget.ArticleCard
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun ArticlePage(
    navController: NavHostController,
) {
    PlanDuitScaffold(
        title = "Artikel",
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
                (0..10).forEach {
                    ArticleCard(
                        title = "Article $it ",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                        date = "01 JAN 2024",
                        onTap = {
                            navController.navigate(AppRoute.ArticleDetail.route)
                        }
                    )
                }
                ArticleCard(
                    title = "Article terakhie ",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                    date = "01 JAN 2024",
                    hideDivider = true,
                    onTap = {
                        navController.navigate(AppRoute.ArticleDetail.route)
                    }
                )
            }

    }
}
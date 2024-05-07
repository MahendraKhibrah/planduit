package com.pens.planduit.presentation.features.article.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.theme.SmallGrey
import com.pens.planduit.presentation.features.article.widget.ArticleCard
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun ArticleDetailPage(
    navController: NavHostController,
) {
    PlanDuitScaffold(
        title = "Artikel",
        onBackPressed = {
            navController.popBackStack()
        },
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(DarkGrey, shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                style = BalanceBlack.copy(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("30 Mar 2024", style = SmallGrey)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque in turpis in felis sodales bibendum. Nullam convallis, justo at tempus dictum, metus lectus tincidunt velit, vel facilisis lorem justo sed lorem. Donec ut fermentum ex. Cras interdum elit lacus, sed porta lacus tempus eu. Aliquam lobortis augue purus, eleifend euismod nibh rutrum a. Vestibulum gravida ullamcorper ipsum at tincidunt. Etiam tristique dui ac semper congue. Fusce placerat placerat ex sed dapibus.\n" +
                        "\n" +
                        "Pellentesque in neque quis est tincidunt pharetra. Phasellus luctus accumsan rutrum. Donec at scelerisque ipsum, vel ultricies sapien. Sed at rutrum diam. Cras aliquet facilisis ultrices. Integer blandit elit et fermentum vehicula. Aliquam erat volutpat. Fusce auctor quis metus non gravida. Duis mauris ante, lacinia pellentesque libero ut, tincidunt pulvinar neque.\n" +
                        "\n" +
                        "Pellentesque aliquam erat vel pellentesque ornare. Nullam tristique orci sapien, eleifend placerat lorem elementum ultrices. Donec laoreet a erat in rhoncus. Suspendisse lacinia lectus in erat rhoncus, mollis pulvinar mi maximus. Morbi pulvinar laoreet mauris, non vestibulum risus porttitor sit amet. Donec et tincidunt justo. Nam condimentum augue velit, in fermentum tellus convallis sed. Aliquam laoreet nulla est. Morbi urna mauris, malesuada a egestas vitae, vestibulum et erat. Nam lobortis eu ante sed egestas. Aliquam molestie, nisi nec tincidunt egestas, magna sapien lobortis justo, vitae gravida neque ligula eu libero. Praesent semper sapien mauris, non vestibulum ipsum faucibus at.\n",
                style = SmallBlack
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Artikel Lainnya", style = BalanceBlack)
            Spacer(modifier = Modifier.height(20.dp))
                ArticleCard(
                    thumbnailUrl = "",
                    title = "Article 1 ",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                    date = "01 JAN 2024",
                    onTap = {
                        navController.popBackStack()
                        navController.navigate(AppRoute.ArticleDetail.route)
                    }
                )
            ArticleCard(
                thumbnailUrl = "",
                title = "Article 2 ",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                date = "01 JAN 2024",
                hideDivider = true,
                onTap = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.ArticleDetail.route)
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            GradientContainer(gradientColors = listOf(Color.Transparent), borderColor = BlackPrimary, onPressed = {
                navController.popBackStack()
                navController.navigate(AppRoute.Article.route)
            }) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ){
                    Text(text = "Lihat Semua Artikel", style = BalanceBlack, modifier = Modifier.padding(vertical = 6.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

    }
}

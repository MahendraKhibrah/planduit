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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.components.container.ShimmerBox
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.theme.SmallGrey
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.presentation.features.article.viewModel.ArticleDetailViewModel

@Composable
fun ArticleDetailPage(
    navController: NavHostController,
    viewModel : ArticleDetailViewModel = hiltViewModel<ArticleDetailViewModel>(),
    slug : String,
) {
    val state = viewModel.articleState.collectAsStateWithLifecycle()
    var isImageError by remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LaunchedEffect(true){
        viewModel.getArticles(slug)
    }

    PlanDuitScaffold(
        title = "Artikel",
        onBackPressed = {
            navController.popBackStack()
        },
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))

            if (state.value.isLoading) {
                ShimmerBox(width = screenWidth, height = 200.dp)
            } else if (isImageError) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(DarkGrey, shape = RoundedCornerShape(8.dp))
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.value.data?.thumbnail ?: "")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(
                            RoundedCornerShape(8)
                        ),
                    onError = {
                        isImageError = true
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            if (state.value.isLoading) {
                ShimmerBox(width = screenWidth.times(0.5f), height = 18.dp)
            } else {
                Text(
                    text = state.value.data?.title ?: "",
                    style = BalanceBlack.copy(fontSize = 18.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (state.value.isLoading) {
                ShimmerBox(width = screenWidth.times(0.2f), height = 12.dp)
            } else {
                Text(
                    text = Utils.formatDate(state.value.data?.createdAt ?: "2024-04-25T16:29:32.000000Z"),
                    style = SmallGrey
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            if (state.value.isLoading) {
                (0..4).forEach { _ ->
                    ShimmerBox(width = screenWidth, height = 12.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                Text(
                    text = state.value.data?.description ?: "",
                    style = SmallBlack
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            GradientContainer(gradientColors = listOf(Color.Transparent), borderColor = BlackPrimary, onPressed = {
                navController.popBackStack()
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

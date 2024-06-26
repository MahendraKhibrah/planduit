package com.pens.planduit.presentation.features.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pens.planduit.common.R
import com.pens.planduit.common.components.gradient.GradientCircle
import com.pens.planduit.common.theme.GreenSecondary
import com.pens.planduit.common.theme.PaleBlue
import com.pens.planduit.presentation.navigation.AppRoute
import kotlinx.coroutines.delay
import com.airbnb.lottie.compose.*

@Composable
fun SplashPage(
    navController: NavHostController,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.planduit_splash)
    )

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(AppRoute.Main.route){
            popUpTo(AppRoute.Splash.route){
                inclusive = true
            }
        }
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .size(width = screenWidth, height = screenHeight)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier.offset(x = screenWidth.times(0.4f) * -1,  y = screenHeight.times(0.2f) * -1)
        ) {
            GradientCircle(color = GreenSecondary)
        }
        Box(
            modifier = Modifier.offset(x = screenWidth.times(0.3f), y = screenHeight.times(0.7f))
        ) {
            GradientCircle(color = PaleBlue)
        }
        Box(modifier = Modifier.width(screenWidth).height(screenHeight)) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

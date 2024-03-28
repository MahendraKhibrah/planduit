package com.pens.planduit.presentation.features.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.components.container.PlanDuitScaffold
import com.pens.planduit.common.theme.GreenSecondary
import com.pens.planduit.common.theme.OffYellow
import com.pens.planduit.common.R
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.BalanceGrey
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.BoldBalanceBlack
import com.pens.planduit.common.theme.LeadingGreen
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.presentation.features.article.widget.ArticleCard
import com.pens.planduit.presentation.features.main.widget.MenuItem
import com.pens.planduit.presentation.navigation.AppRoute

@Composable
fun HomePage(
    navController: NavHostController,
) {
    val interactionSource = remember { MutableInteractionSource() }
    PlanDuitScaffold(
        showAppBar = false
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            GradientContainer(
                gradientColors = listOf(GreenSecondary, OffYellow),
                showShadow = true,
                cornerRadius = 16
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    Column {
                        Text("Hitung Investasi & \nKelola Keuangan Anda!", style = BalanceBlack)
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = MediumBlack.copy(
                                        color = BlackPrimary,
                                        fontSize = 14.sp
                                    ).toSpanStyle()
                                ) {
                                    append("Gunakan ")
                                }
                                withStyle(
                                    style = BoldBalanceBlack.copy(color = Color(0xFF28D177))
                                        .toSpanStyle()
                                ) {
                                    append("Planduit")
                                }
                                withStyle(
                                    style = MediumBlack.copy(
                                        color = BlackPrimary,
                                        fontSize = 14.sp
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
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Kalkulator Investasi", style = LeadingGreen.copy(fontSize = 16.sp))
                Spacer(modifier = Modifier.weight(1f))
//                Text(text = "Lihat Semua", style = BalanceGrey.copy(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                MenuItem(title = "Profil Resiko", imageId = R.drawable.ic_risk_profile, onPressed = {
                    navController.navigate(AppRoute.RiskProfile.route)
                })
                Spacer(modifier = Modifier.weight(1f))
                MenuItem(title = "Investasi", imageId = R.drawable.ic_investation, onPressed = {
                    navController.navigate(AppRoute.InvestmentCalculator.route)
                })
                Spacer(modifier = Modifier.weight(1f))
                MenuItem(title = "Impian", imageId = R.drawable.ic_dream)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                MenuItem(title = "Budgeting 50/30/20", imageId = R.drawable.ic_budgeting, onPressed = {
                    navController.navigate(AppRoute.Budgeting.route)
                })
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Kalkulator Zakat", style = LeadingGreen.copy(fontSize = 16.sp))
                Spacer(modifier = Modifier.weight(1f))
//                Text(text = "Lihat Semua", style = BalanceGrey.copy(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                MenuItem(title = "Perdagangan", imageId = R.drawable.ic_trading)
                Spacer(modifier = Modifier.weight(1f))
                MenuItem(title = "Penghasilan", imageId = R.drawable.ic_income, onPressed = {
                    navController.navigate(AppRoute.ZakatIncome.route)
                })
                Spacer(modifier = Modifier.weight(1f))
                MenuItem(title = "Emas dan Perak", imageId = R.drawable.ic_gold, onPressed = {
                    navController.navigate(AppRoute.ZakatGold.route)
                } )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                MenuItem(title = "Pertanian", imageId = R.drawable.ic_agriculture, onPressed = {
                    navController.navigate(AppRoute.ZakatAgriculture.route)
                })
                Spacer(modifier = Modifier.weight(1f))
                MenuItem(title = "Tabungan", imageId = R.drawable.ic_saving)
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.width(83.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Artikel", style = LeadingGreen.copy(fontSize = 16.sp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Lihat Semua", style = BalanceGrey.copy(fontSize = 12.sp), modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        navController.navigate(AppRoute.Article.route)
                    }
                ))
            }
            Spacer(modifier = Modifier.height(24.dp))
            ArticleCard(
                title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                date = "30 MAR 2024",
                onTap = {
                    navController.navigate(AppRoute.ArticleDetail.route)
                }
            )
            ArticleCard(
                title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                date = "28 FEB 2024",
                onTap = {
                    navController.navigate(AppRoute.ArticleDetail.route)
                }
            )
            ArticleCard(
                title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique ultrices sem, eget aliquet velit pretium vel. ",
                date = "01 JAN 2024",
                onTap = {
                    navController.navigate(AppRoute.ArticleDetail.route)
                },
                hideDivider = true
            )
        }
    }
}
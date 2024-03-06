package com.pens.planduit.presentation.features.main.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pens.planduit.presentation.features.main.viewModel.TestingViewModel

@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: TestingViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = Unit) {
        viewModel.getInvestmentNotAchieved()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(text = "this is first page")
            Button(onClick = {
            }) {
                Text(text = "navigate to second page")
            }
            Button(onClick = {
            }) {
                Text(text = "navigate to main page")
            }
        }
    )
}
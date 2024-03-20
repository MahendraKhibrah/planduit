package com.pens.planduit.common.components.container

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.R
import com.pens.planduit.common.theme.BalanceBlack
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.BottomSheetData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonBottomSheet(
    data: BottomSheetData,
    isOpen: Boolean = false,
    onDismiss: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(isOpen) }
    LaunchedEffect(isOpen) {
        isSheetOpen = isOpen
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = Color.White,
            sheetState = sheetState,
            onDismissRequest = {
                onDismiss()
                isSheetOpen = false
            },
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = data.title, style = BalanceBlack
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = data.imageId),
                    contentDescription = null,
                    modifier = Modifier.height(110.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data.description,
                    style = SmallBlack.copy(fontSize = 14.sp),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CommonBottomSheetPreview() {
//    PlanDuitScaffold(
//        bottomSheet = {
//            CommonBottomSheet(
//                data = BottomSheetData(
//                    title = "Apa itu Investasi?",
//                    description = "Investasi adalah penanaman modal dalam bentuk uang atau aset lainnya dengan tujuan memperoleh keuntungan di masa depan. Investasi dapat dilakukan dalam berbagai bentuk, seperti saham, reksa dana, properti, dan lain-lain.",
//                    imageId = R.drawable.ic_investation
//                ),
//                isOpen = true
//            )
//        }
//    ) {
//
//    }
//}
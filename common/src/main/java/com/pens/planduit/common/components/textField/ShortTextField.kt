package com.pens.planduit.common.components.textField

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.BlackPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils

@Composable
fun ShortTextField(
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
    onValueChange: (String) -> Unit = {},
    trailingWidget: @Composable () -> Unit = {},
    keyboardActions: KeyboardActions? = null,
    onDone: (String) -> Unit = {},
    value: String = ""
) {
    var text by remember { mutableStateOf(value) }
    var isFocused by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BasicTextField(
                value = text,
                onValueChange = { newValue ->
                    if (newValue.length < 3) {
                        text = Utils.cleaningNumberString(newValue)
                        onValueChange(newValue)
                    }
                },
                maxLines = 1,
                textStyle = SmallBlack.copy(fontSize = 14.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .width(50.dp)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions ?: KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onDone(text)
                    }
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .width(100.dp)
                    .border(BorderStroke(1.dp, if (isFocused) BlackPrimary else HalfGrey))
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        trailingWidget()
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
//@Composable
//fun PreviewCustomTextField() {
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val focusManager = LocalFocusManager.current
//
//    var text by remember { mutableStateOf(TextFieldValue()) }
//
//    Box(
//        modifier = Modifier
//            .size(width = screenWidth, height = screenHeight)
//            .padding(16.dp)
//            .pointerInput(Unit) {
//                detectTapGestures {
//                    focusManager.clearFocus()
//                }
//            }
//    ) {
//        Column {
//            ShortTextField(
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Email,
//                    imeAction = ImeAction.Next
//                ),
//                trailingWidget = { Text(text = "Tahun", style = SmallBlack.copy(fontSize = 14.sp)) }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            OutlinedTextField(
//                value = text,
//                onValueChange = {
//                    val rawNumber = Utils.removeCommas(it.text)
//                    if (rawNumber.length <= 11){
//                        val formattedNumber = Utils.addCommasEveryThreeChars(rawNumber)
//                        val newPosition = formattedNumber.length
//                        text = it.copy(text = formattedNumber, selection = TextRange(newPosition, newPosition))
//                    }
//                },
//                prefix = { Text("Rp ", style = SmallBlack.copy(fontSize = 14.sp)) },
//                modifier = Modifier
//                    .width(screenWidth)
//                    .border(
//                        1.dp,
//                        HalfGrey,
//                        RoundedCornerShape(13.dp)
//                    ), // Menyesuaikan outline dan corner radius
//                textStyle = SmallBlack.copy(fontSize = 14.sp),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Number,
//                    imeAction = ImeAction.Done
//                ),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color.Red.copy(0f), // Warna outline saat aktif
//                    unfocusedBorderColor = Color.Red.copy(0f) // Warna outline saat tidak aktif
//                )
//            )
//        }
//    }
//}

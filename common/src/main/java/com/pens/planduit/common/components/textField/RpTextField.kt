package com.pens.planduit.common.components.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.SmallBlack
import com.pens.planduit.common.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RpTextField(
    modifier: Modifier = Modifier,
    hideLeading : Boolean = false,
    onValueChange: (String) -> Unit = {},
    onDone : (String) -> Unit = {},
    value: String = ""
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val focusManager = LocalFocusManager.current

    var text by remember { mutableStateOf(TextFieldValue(text = value)) }

    LaunchedEffect(value) {
        text = TextFieldValue(text = value)
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            val rawNumber = Utils.cleaningNumberString(it.text)
            if (rawNumber.length <= 11) {
                onValueChange(rawNumber)
                val formattedNumber = Utils.addCommasEveryThreeChars(rawNumber)
                val newPosition = formattedNumber.length
                text =
                    it.copy(text = formattedNumber, selection = TextRange(newPosition, newPosition))
            }
        },
        prefix = { if(!hideLeading) Text("Rp ", style = SmallBlack.copy(fontSize = 14.sp)) },
        modifier = modifier
            .width(screenWidth)
            .border(
                1.dp,
                HalfGrey,
                RoundedCornerShape(13.dp)
            ),
        textStyle = SmallBlack.copy(fontSize = 14.sp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onDone(text.text)
            }
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red.copy(0f),
            unfocusedBorderColor = Color.Red.copy(0f)
        )
    )
}


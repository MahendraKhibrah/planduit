package com.pens.planduit.common.components.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pens.planduit.common.theme.BalanceGrey
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.SmallBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    onSearch: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        placeholder = {
            Text(placeHolder, style = BalanceGrey)
        },
        modifier = modifier
            .width(screenWidth)
            .border(
                1.dp,
                DarkGrey,
                RoundedCornerShape(13.dp)
            ),
        textStyle = SmallBlack.copy(fontSize = 14.sp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(text)
                focusManager.clearFocus()
            }
        ),
        trailingIcon = {
            IconButton(onClick = { onSearch(text) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red.copy(0f),
            unfocusedBorderColor = Color.Red.copy(0f)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchTextField() {
    SearchTextField(
        placeHolder = "Cari artikel"
    )
}
package com.pens.planduit.presentation.features.main.widget

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.pens.planduit.common.components.container.GradientContainer
import com.pens.planduit.common.theme.DarkGrey
import com.pens.planduit.common.theme.GreenPrimary
import com.pens.planduit.common.theme.HalfGrey
import com.pens.planduit.common.theme.MediumBlack
import com.pens.planduit.common.theme.MediumWhite
import com.pens.planduit.common.theme.SmallBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingDialog(
    onDismiss: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    var rating: Float by remember { mutableFloatStateOf(0.0f) }
    var text by remember { mutableStateOf(TextFieldValue(text = "")) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    AlertDialog(onDismissRequest = onDismiss, confirmButton = {},
        icon = {
          Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
              Icon(imageVector = Icons.Default.Close, contentDescription = "close", modifier = Modifier.clickable(
                  interactionSource = remember { MutableInteractionSource() },
                  indication = null,
                  onClick = {
                      onDismiss()
                  },
              ))
          }  
        },
        text = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Kasih Rating, yuk!", style = MediumBlack
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            RatingBar(
                value = rating,
                onValueChange = {
                    rating = it
                },
                onRatingChanged = {
                    Log.d("TAG", "onRatingChanged: $it")
                },
                config = RatingBarConfig().inactiveColor(HalfGrey)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Ada lagi yang ingin disampaikan?",
                style = SmallBlack.copy(fontSize = 14.sp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = {
                    Text(
                        text = "Berikan komentar kamu...",
                        style = SmallBlack.copy(fontSize = 14.sp, color = HalfGrey)
                    )
                },
                minLines = 4,
                maxLines = 8,
                modifier = Modifier
                    .width(screenWidth)
                    .border(
                        1.dp,
                        HalfGrey,
                        RoundedCornerShape(13.dp)
                    )
                    .imePadding(),
                textStyle = SmallBlack.copy(fontSize = 14.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red.copy(0f),
                    unfocusedBorderColor = Color.Red.copy(0f)
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            SubmitButton(isActive = rating > 0) {
                onDismiss()
            }
        }
    })
}

@Composable
private fun SubmitButton(
    isActive: Boolean = false,
    onPressed: () -> Unit = {}
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        GradientContainer(
            gradientColors = listOf(if (isActive) GreenPrimary else HalfGrey),
            modifier = Modifier.size(screenWidth, 45.dp),
            cornerRadius = 12,
            onPressed = {
                if (isActive) {
                    onPressed()
                }

            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Kirim Rating",
                    style = MediumWhite.copy(color = if (isActive) Color.White else DarkGrey)
                )
            }
        }
    }
}



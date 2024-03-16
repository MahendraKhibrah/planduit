package com.pens.planduit.common.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// -------------------- GREEN PRIMARY -------------------//
val LeadingGreen = TextStyle(
    color = GreenPrimary,
    fontSize = 20.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp
)

val BalanceGreen = TextStyle(
    color = GreenPrimary,
    fontSize = 14.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.5.sp
)

//-------------------  BLACK  --------------------------//
val BoldBalanceBlack = TextStyle(
    color = Color.Black,
    fontSize = 14.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.5.sp
)

val BalanceBlack = TextStyle(
    color = Color.Black,
    fontSize = 14.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.5.sp
)

val MediumBlack = TextStyle(
    color = Color.Black,
    fontSize = 16.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp
)

val SmallBlack = TextStyle(
    color = Color.Black,
    fontSize = 12.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Light,
    letterSpacing = 0.5.sp
)

//-------------------  GREY  --------------------------//
val BalanceGrey = TextStyle(
    color = DarkGrey,
    fontSize = 14.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.5.sp
)

val SmallGrey = TextStyle(
    color = DarkGrey,
    fontSize = 12.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Light,
    letterSpacing = 0.5.sp
)

val MediumGrey = TextStyle(
    color = DarkGrey,
    fontSize = 16.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp
)

//-------------------  WHITE  --------------------------//
val BalanceWhite = TextStyle(
    color = Color.White,
    fontSize = 14.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.5.sp
)

val MediumWhite = TextStyle(
    color = Color.White,
    fontSize = 16.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp
)

//-------------------  RED  --------------------------//
val MediumCrossedRed = TextStyle(
    color = BoldRed,
    fontSize = 14.sp,
    fontFamily = poppins,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.5.sp,
    textDecoration = TextDecoration.LineThrough
)

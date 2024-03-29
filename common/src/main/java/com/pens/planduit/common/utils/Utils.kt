package com.pens.planduit.common.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

object Utils {
    fun addCommasEveryThreeChars(input: String): String {
        if (input.length <= 3) {
            return input // Jika panjang string kurang dari atau sama dengan 3, kembalikan input asli
        }

        val reversedInput = input.reversed() // Membalik string input
        val result = StringBuilder()

        for (i in reversedInput.indices step 3) {
            val endIndex = minOf(i + 3, reversedInput.length)
            result.append(reversedInput.substring(i, endIndex))

            if (endIndex < reversedInput.length) {
                result.append(',')
            }
        }

        return result.reverse().toString()
    }

    fun addCommasEveryThreeChars(input: Int): String {
        return addCommasEveryThreeChars(input.toString())
    }

    fun removeCommas(input: String): String {
        return input.replace(",", "")
    }

    fun removeCommasToInt(input: String): Int {
        return removeCommas(input).toInt()
    }

    fun createRpText(input: Int?): String {
        var result = "Rp. "
        if (input == null) {
            result += "-"
            return result
        }
        result += addCommasEveryThreeChars(input)
        return result
    }

    fun createPercentText(input: Int?): String {
        var result = ""
        if (input == null) {
            result += "-"
            return result
        }
        result += "$input %"
        return result
    }

    fun convertCommasToList(input: String): List<String> {
        return input.split(", ")
    }

    fun getTextWidth(fontSize: Float, textLength: Int): Dp {
        return (fontSize * textLength * 0.6f).dp
    }

    fun getTextHeight(fontSize: Float): Dp {
        return (fontSize * 1.5f).dp.minus(2.dp)
    }
}
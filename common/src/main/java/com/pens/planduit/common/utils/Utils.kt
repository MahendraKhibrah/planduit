package com.pens.planduit.common.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {
    fun addCommasEveryThreeChars(input: String): String {
        if (input.length <= 3) {
            return input
        }

        val reversedInput = input.reversed()
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

    fun addCommasEveryThreeChars(input: Number): String {
        return addCommasEveryThreeChars(input.toLong().toString())
    }

    fun removeCommas(input: String): String {
        return input.replace(",", "")
    }

    fun removeDot(input: String): String {
        return input.replace(".", "")
    }

    fun removeStrip(input: String) : String{
        return input.replace("-", "")
    }

    fun removeSpace(input: String): String {
        return input.replace(" ", "")
    }

    fun cleaningNumberString(input: String): String {
        return removeCommas(removeDot(removeStrip(removeSpace(input))))
    }

    fun removeCommasToInt(input: String): Number {
        return removeCommas(input).toLong()
    }

    fun createRpText(input: Number?): String {
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


    fun formatDate(date: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.ENGLISH)
        inputFormatter.timeZone = TimeZone.getTimeZone("UTC")
        val parsedDate = inputFormatter.parse(date)
        val outputFormatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        return outputFormatter.format(parsedDate ?: Date())
    }
}
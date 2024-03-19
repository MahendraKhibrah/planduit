package com.pens.planduit.common.utils

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

}
package core.util

import java.text.DecimalFormat
import java.util.*

fun Double.setNumberFormatCommas(): String {
    return DecimalFormat("###,###,###.##").format(this)
}

fun Double.setStringCommasAndDigit(): String {
    return String.format(Locale.getDefault(), "%,.2f", this)
}

fun Double.setStringCommasAndDigit(digit: Int): String {
    return String.format(Locale.getDefault(), "%,." + digit + "f", this)
}

fun Double.setNumberFormatDigit(): String {
    val number = String.format(Locale.getDefault(), "%,.2f", this)
    val newNumber = number.split(".")
    if (Integer.parseInt(newNumber[1]) > 0) {
        return number
    }
    return newNumber[0]
}
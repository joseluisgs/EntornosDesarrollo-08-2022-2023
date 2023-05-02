@file:JvmName("UtilsKotlin")

package mikotlin

import java.text.SimpleDateFormat
import java.util.*


fun getFechaActual(): String {
    return SimpleDateFormat("dd/MM/yyyy").format(Date())
}

fun String.toTitleCase(): String {
    return this.split(" ")
        .joinToString(" ") { palabra -> palabra.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
}
package com.jmm.womensafety.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


private val SDF_YMD_WITH_DASH = SimpleDateFormat("yyyy-MM-dd", Locale.US)
val SDF_d_M_y = SimpleDateFormat("dd MMM yyyy", Locale.US)
val SDF_dM = SimpleDateFormat("dd MMM", Locale.US)
val SDF_dmyhms = SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.US)
fun convertISOTimeToDateTime(isoTime: String): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    var convertedDate: Date? = null
    var formattedDate: String? = null
    try {
        convertedDate = sdf.parse(isoTime)
        formattedDate = SimpleDateFormat(" dd MMM yyyy").format(convertedDate)
//        formattedDate = SimpleDateFormat("MMM dd,yyyy | HH:mm").format(convertedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}

fun formatDate(date: String, inputFormat: String, outputFormat: String): String {
    val inSdf = SimpleDateFormat(inputFormat, Locale.ENGLISH)
    val outSdf = SimpleDateFormat(outputFormat, Locale.ENGLISH)

    return try {
        val inputDate = inSdf.parse(date)
        outSdf.format(inputDate!!)
    } catch (e: ParseException) {
        e.printStackTrace()
        "error"
    }

}

fun convertISOTimeToDate(isoTime: String): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    var convertedDate: Date? = null
    var formattedDate: String? = null
    try {
        convertedDate = sdf.parse(isoTime)
        formattedDate = SimpleDateFormat("MMM dd,yyyy").format(convertedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}

fun convertTimeStampToDate(isoTime: String): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    var convertedDate: Date? = null
    var formattedDate: String? = null
    try {
        convertedDate = sdf.parse(isoTime)
        formattedDate = SimpleDateFormat("MMM dd,yyyy").format(convertedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}

fun convertISOTimeToAny(isoTime: String, myFormatter: SimpleDateFormat): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    var convertedDate: Date? = null
    var formattedDate: String? = null
    try {
        convertedDate = sdf.parse(isoTime)
        formattedDate = myFormatter.format(convertedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}

fun IntToOrdinal(i: Int): String {
    val j = i % 10
    val k = i % 100
    if (j == 1 && k != 11) {
        return i.toString() + "st"
    }
    if (j == 2 && k != 12) {
        return i.toString() + "nd"
    }
    return if (j == 3 && k != 13) {
        i.toString() + "rd"
    } else i.toString() + "th"
}

fun getTodayDate(): String {
    return getDaysAgo(0)
}


fun getDaysAgo(daysAgo: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return SDF_YMD_WITH_DASH.format(calendar.time)
}

fun getDayAgo(daysAgo: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysAgo)
    return SDF_YMD_WITH_DASH.format(calendar.time)
}

fun convertMillisecondsToDate(milliSeconds: Long?, dateFormat: String?): String {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = SimpleDateFormat(dateFormat)

    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar = Calendar.getInstance()
    if (milliSeconds != null) {
        calendar.timeInMillis = milliSeconds
    }
    return formatter.format(calendar.time)
}


fun convertSecondsTimeToDate(seconds: Long, dateFormat: String): String {
    val sdf = SimpleDateFormat(dateFormat, Locale.US)
    return sdf.format(Date(seconds))
}
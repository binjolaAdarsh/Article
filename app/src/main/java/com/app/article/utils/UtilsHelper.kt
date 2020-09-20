package com.app.article.utils

import android.view.View
import androidx.databinding.BindingAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow


const val BASE_URL="https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"
const val DEBUG_TAG = "debug_tag"
const val LIMIT = 10
const val DATABASE_NAME = "article.db"

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

fun dateGetTimeAgo(time:String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.ENGLISH)
   var date:Date
    try {
        date = input.parse(time)
    } catch (e: ParseException) {
        e.printStackTrace()
        return ""
    }

    val calendar = Calendar.getInstance()
    calendar.time = date

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val currentCalendar = Calendar.getInstance()

    val currentYear = currentCalendar.get(Calendar.YEAR)
    val currentMonth = currentCalendar.get(Calendar.MONTH)
    val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
    val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = currentCalendar.get(Calendar.MINUTE)

    return if (year < currentYear ) {
        val interval = currentYear - year
        if (interval == 1) "$interval Year" else "$interval Years"
    } else if (month < currentMonth) {
        val interval = currentMonth - month
        if (interval == 1) "$interval Month" else "$interval Months"
    } else  if (day < currentDay) {
        val interval = currentDay - day
        if (interval == 1) "$interval Day" else "$interval Days"
    } else if (hour < currentHour) {
        val interval = currentHour - hour
        if (interval == 1) "$interval hr" else "$interval hr" // for hour
    } else if (minute < currentMinute) {
        val interval = currentMinute - minute
        if (interval == 1) "$interval min" else "$interval min" // for minute
    } else {
        "moment ago"
    }
}

 fun numberCalculation(number: Int): String? {
    if (number < 1000) return "" + number
    val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.1f%c",
        number / 1000.0.pow(exp.toDouble()),
        "kMGTPE"[exp - 1]
    )
}

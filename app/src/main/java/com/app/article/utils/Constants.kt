package com.app.article.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


const val BASE_URL="https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"
const val DEBUG_TAG = "debug_tag"
const val LIMIT = 10

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

fun dategetTimeAgo(time:String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
        if (interval == 1) "$interval year" else "$interval years"
    } else if (month < currentMonth) {
        val interval = currentMonth - month
        if (interval == 1) "$interval month" else "$interval months"
    } else  if (day < currentDay) {
        val interval = currentDay - day
        if (interval == 1) "$interval day" else "$interval days"
    } else if (hour < currentHour) {
        val interval = currentHour - hour
        if (interval == 1) "$interval hr" else "$interval hr" // for hour
    } else if (minute < currentMinute) {
        val interval = currentMinute - minute
        if (interval == 1) "$interval min" else "$interval min" // for minute
    } else {
        "a moment ago"
    }
}

 fun numberCalculation(number: Int): String? {
    if (number < 1000) return "" + number
    val exp = (Math.log(number.toDouble()) / Math.log(1000.0)).toInt()
    return String.format(
        "%.1f%c",
        number / Math.pow(1000.0, exp.toDouble()),
        "kMGTPE"[exp - 1]
    )
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(
        this.context,
        DividerItemDecoration.VERTICAL
    )
    val drawable = ContextCompat.getDrawable(
        this.context,
        drawableRes
    )
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}
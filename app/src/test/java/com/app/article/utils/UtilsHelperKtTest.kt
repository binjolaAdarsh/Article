package com.app.article.utils

import org.junit.Test

import org.junit.Assert.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

class UtilsHelperKtTest {
    val time: String? = "2020-04-17T12:13:44.575Z"
    val timeResult: String? = "5 Months"
    val likes = 8237
    val likesResult = "8.2k"
    val comments = 86439
    val commentsResult = "86.4k"



    @Test
    fun dateGetTimeAgo() {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        var date: Date?=null
        try {
            date = input.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()

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

        val result= if (year < currentYear ) {
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
        assertEquals(result, timeResult)

    }

    @Test
    fun numberCalculation() {
        var result:String
        result = if (likes < 1000) "" + likes
        else{

            val exp = (ln(likes.toDouble()) / ln(1000.0)).toInt()
            String.format(
                "%.1f%c",
                likes / 1000.0.pow(exp.toDouble()),
                "kMGTPE"[exp - 1]
            )
        }
        assertEquals(result, likesResult)
    }
}
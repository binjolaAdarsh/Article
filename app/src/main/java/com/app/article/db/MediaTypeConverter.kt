package com.app.article.db

import androidx.room.TypeConverter
import com.app.article.model.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MediaTypeConverter {
    private val gson = Gson()
    private val type: Type =
        object : TypeToken<List<Media>>() {}.type


    @TypeConverter
    fun stringToMedia(json: String?): List<Media>? {
        if (json == null) {
            return null
        }
        return gson.fromJson(json, type)
    }


    @TypeConverter
    fun mediaToString(media: List<Media>?): String? {
        if (media == null) {
            return null
        }
        return gson.toJson(media, type)
    }
}
package com.app.article.db

import androidx.room.TypeConverter
import com.app.article.model.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MediaTypeConverter {
    private val gson = Gson()
    private val type: Type =
        object : TypeToken<Media>() {}.type


    @TypeConverter
    fun stringToMedia(json: String?): Media? {
        return gson.fromJson(json, type)
    }


    @TypeConverter
    fun mediaToString(media: Media): String? {
        return gson.toJson(media, type)
    }
}
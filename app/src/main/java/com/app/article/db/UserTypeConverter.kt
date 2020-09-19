package com.app.article.db

import androidx.room.TypeConverter
import com.app.article.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UserTypeConverter {
    private val gson = Gson()
    private val type: Type =
        object : TypeToken<User>() {}.type

    @TypeConverter
    fun stringToUser(json: String?): User? {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun userToString(user: User): String? {
        return gson.toJson(user, type)
    }
}
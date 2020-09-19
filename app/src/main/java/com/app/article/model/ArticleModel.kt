package com.app.article.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.app.article.db.MediaTypeConverter
import com.app.article.db.UserTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ArticleTable" )
data class ArticleModel(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("createdAt")
    @Expose
    val createdAt: String,
    @SerializedName("content")
    @Expose
    val content: String,
    @SerializedName("comments")
    @Expose
    val comments: Int,
    @SerializedName("likes")
    @Expose
    val likes: Int
//    @SerializedName("media")
//    @Expose
//    val media: List<Media>?=null,
//    @SerializedName("user")
//    @Expose
//    val user: List<User>?=null
)

data class Media(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("blogId")
    @Expose
    val blogId: String,
    @SerializedName("createdAt")
    @Expose
    val createdAt: String,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("url")
    @Expose
    val url: String
)

data class User(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("blogId")
    @Expose
    val blogId: String,
    @SerializedName("createdAt")
    @Expose
    val createdAt: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("avatar")
    @Expose
    val avatar: String,
    @SerializedName("lastname")
    @Expose
    val lastName: String,
    @SerializedName("city")
    @Expose
    val city: String,
    @SerializedName("designation")
    @Expose
    val designation: String,
    @SerializedName("about")
    @Expose
    val about: String
)
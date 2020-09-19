package com.app.article.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "ArticleTable" )
data class ArticleModel(
@PrimaryKey
 @field:SerializedName("id")
    val id: String,
 var localId: Int,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("comments")
    val comments: Int,

    @field:SerializedName("likes")
    val likes: Int,

    @field:SerializedName("media")
    val media: List<Media>?=null,

    @field:SerializedName("user")
    val user: List<User>?=null
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
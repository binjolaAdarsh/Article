package com.app.article.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.article.utils.dateGetTimeAgo
import com.app.article.utils.numberCalculation
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * used to collect the response of the api call
 */
data class ArticleModel(
 @field:SerializedName("id")
    val id: String,

    @field:SerializedName("createdAt")
    val createdAt: String="",

    @field:SerializedName("content")
    val content: String="",

    @field:SerializedName("comments")
    val comments: Int=0,

    @field:SerializedName("likes")
    val likes: Int=0,

    @field:SerializedName("media")
    val media: List<Media?>?=null,

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

/**
 * we use this class to store it as a table in the room db with the name ArticleTable
 */
@Entity(tableName = "ArticleTable" )
data class Article(
    @PrimaryKey
    val articleId:Int,
    val userImageUrl:String?="",
    val userName:String?="",
    val userDesignation:String?="",
    val articleTime:String?="",
    val articleImageUrl:String?="",
    val articleContent:String?="",
    val articleTitle:String?="",
    val articleUrl:String?="",
    val articleLikes:String?="",
    val articleComments:String?=""
){
    companion object{
        fun create(articleModel: ArticleModel):Article{
            articleModel.apply {
                val user=user?.get(0)
                val isMediaPresent=media?.size!! > 0
                var media: Media?=null
                if(isMediaPresent ){
                    media = this.media[0]
                }

               return Article(id.toInt(), user?.avatar,
                    userName = "${user?.name} ${user?.lastName}",
                    userDesignation = user?.designation,
                    articleTime = dateGetTimeAgo(createdAt),
                    articleImageUrl = if(isMediaPresent) media?.image else "",
                    articleContent = content,
                    articleTitle =if(isMediaPresent) media?.title else "" ,
                    articleUrl = if(isMediaPresent) media?.url else ""   ,
                    articleLikes = "${numberCalculation(likes)} Likes",
                    articleComments = "${numberCalculation(comments)} Comments"
                )
            }

        }
    }

}
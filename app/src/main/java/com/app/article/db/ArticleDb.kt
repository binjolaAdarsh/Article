package com.app.article.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.article.model.Article

@Database(
    entities = [
        Article::class],
    version =8,
    exportSchema = false
)
// no need of converters because we not not saving whole response model
//@TypeConverters(MediaTypeConverter::class,UserTypeConverter::class)
abstract class ArticleDb : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}

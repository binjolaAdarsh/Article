package com.app.article.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.article.R
import kotlinx.android.synthetic.main.activity_main.*

class ArticlesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}

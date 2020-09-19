package com.app.article.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.article.R
import com.app.article.databinding.ArticleItemBinding
import com.app.article.model.ArticleModel
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class ArticleAdapter(

    var glide: RequestManager
) : RecyclerView.Adapter< ArticleAdapter.ViewHolder>() {

    var list:MutableList<ArticleModel> = mutableListOf<ArticleModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.article_item, parent, false
            ), glide
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(
        private val binding: ArticleItemBinding,

        val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {
        private val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop()



        fun bind(model: ArticleModel) {


            binding.apply {
                article = model
             //   glide.load(model.urlToImage).apply(requestOptions).into(ivBackground)
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
return list.size    }
    fun submitList(list: List<ArticleModel>, page:Int){
        if(page == 1){
            this.list.clear()

        }
        this.list.addAll(list)
        notifyDataSetChanged()


    }
}

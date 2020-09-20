package com.app.article.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.article.R
import com.app.article.databinding.ArticleItemBinding
import com.app.article.model.Article
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class ArticleAdapter(
    var glide: RequestManager
) : RecyclerView.Adapter<ArticleAdapter.ItemViewHolder>() {

    var list: MutableList<Article> = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.article_item, parent, false
            ), glide
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ItemViewHolder(
        private val binding: ArticleItemBinding,
        val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {
        private val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        fun bind(model: Article) {
            binding.apply {
                article = model
                glide.load(model.userImageUrl).apply(requestOptions.circleCrop()).into(ivUserImage)
                glide.load(model.articleImageUrl).apply(requestOptions.centerCrop())
                    .into(ivArticleImage)
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: List<Article>, page: Int) {
        if (page == 1) {
            this.list.clear()
        }
        this.list.addAll(
            list.filter {
                !this.list.contains(it)
            })
        notifyDataSetChanged()
    }

    fun setAdapterList(list: MutableList<Article>) {
        this.list = list
    }
}

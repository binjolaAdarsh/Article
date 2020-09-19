package com.app.article.ui.articles


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.article.R
import com.app.article.databinding.FragmentArticlesBinding
import com.app.article.utils.DEBUG_TAG
import com.app.article.utils.EndlessRecyclerViewScrollListener
import com.app.article.utils.ViewModelProviderFactory
import com.app.headlines.network.NetworkResult
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ArticlesFragment : DaggerFragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var glide: RequestManager
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
private var currentPage=1
    val viewModel: ArticlesViewModel by viewModels {
        providerFactory
    }
    lateinit var dataBinding: FragmentArticlesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate<FragmentArticlesBinding>(
            inflater,
            R.layout.fragment_articles,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleAdapter =
            ArticleAdapter(glide)
        dataBinding.rvArticles.apply {
            this.adapter = articleAdapter
        }
        scrollListener = object :
            EndlessRecyclerViewScrollListener(dataBinding.rvArticles.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView?
            ) { // Triggered only when new data needs to be appended to the list
// Add whatever code is needed to append new items to the bottom of the list
                currentPage=page
                viewModel.getData(page.toString())
                Toast.makeText(requireContext(), "loading more", Toast.LENGTH_SHORT).show()
            }
        }

dataBinding.rvArticles.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
        viewModel.observeNewsData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(DEBUG_TAG, "success")
                    it.data?.let { list ->
                        articleAdapter.submitList(list,currentPage)
                    }
                }
                is NetworkResult.Error -> {
                    Log.d(DEBUG_TAG, "error")
                }
                NetworkResult.InProgress -> {
                    Log.d(DEBUG_TAG, "progress")
                }
            }
        })
    }


}

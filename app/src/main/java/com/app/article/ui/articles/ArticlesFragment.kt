package com.app.article.ui.articles


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.article.R
import com.app.article.databinding.FragmentArticlesBinding
import com.app.article.utils.DEBUG_TAG
import com.app.article.utils.EndlessRecyclerViewScrollListener
import com.app.article.utils.ViewModelProviderFactory
import com.app.headlines.network.NetworkResult
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ArticlesFragment : DaggerFragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var articleAdapter: ArticleAdapter
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
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
        dataBinding.rvArticles.setHasFixedSize(true)
       articleAdapter.setAdapterList(viewModel.list)
        dataBinding.rvArticles.apply {
            this.adapter = articleAdapter
        }
        dataBinding.rvArticles.addItemDecoration( DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        scrollListener = object :
            EndlessRecyclerViewScrollListener(dataBinding.rvArticles.layoutManager as LinearLayoutManager,viewModel.currentPage) {
            override fun onLoadMore(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView?
            ) { // Triggered only when new data needs to be appended to the list
// Add whatever code is needed to append new items to the bottom of the list
                viewModel.currentPage = page
                dataBinding.pbProcessing.visibility = VISIBLE
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
                        dataBinding.pbProcessing.visibility = GONE
                        articleAdapter.submitList(list, viewModel.currentPage)
                    }
                }
                is NetworkResult.Error -> {
                    Log.d(DEBUG_TAG, "error")
                    dataBinding.pbProcessing.visibility = GONE
                }
                NetworkResult.InProgress -> {
                    Log.d(DEBUG_TAG, "progress")
                    if(!dataBinding.pbProcessing.isVisible)
                        dataBinding.pbProcessing.visibility = VISIBLE
                }
            }
        })
    }


}

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.article.R
import com.app.article.utils.DEBUG_TAG
import com.app.article.utils.EndlessRecyclerViewScrollListener
import com.app.article.utils.ViewModelProviderFactory
import com.app.headlines.network.NetworkResult
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_articles.*
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setting the list to adapter to handle the  config change pagination
       articleAdapter.setAdapterList(viewModel.list)

        scrollListener = object :
            EndlessRecyclerViewScrollListener(rvArticles.layoutManager as LinearLayoutManager,viewModel.currentPage) {
            override fun onLoadMore(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView?
            ) {
                // Triggered only when new data needs to be appended to the list
                // initializing currentPage to maintain current page when config change happen
                viewModel.currentPage = page
                pbProcessing.visibility = VISIBLE

                // get the next page data
                viewModel.startJob(page.toString())
            }
        }

        // setting up recyclerView
        rvArticles.apply {
            rvArticles.setHasFixedSize(true)
            this.adapter = articleAdapter
            addItemDecoration( DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
        }

        // observing the data change
        viewModel.observeNewsData().observe(viewLifecycleOwner, Observer {

            when (it) {
                is NetworkResult.Success -> {
                    Log.d(DEBUG_TAG, "success")
                    pbProcessing.visibility = GONE
                    swipeRefresh.isRefreshing = false
                    it.data?.let { list ->
                        articleAdapter.submitList(list, viewModel.currentPage)
                    }
                }
                is NetworkResult.Error -> {
                    Log.d(DEBUG_TAG, "error ${it.exception.message}")
                    swipeRefresh.isRefreshing = false
                    pbProcessing.visibility = GONE
                    Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
                }
                NetworkResult.InProgress -> {
                    Log.d(DEBUG_TAG, "progress")
                    if(!pbProcessing.isVisible)
                        pbProcessing.visibility = VISIBLE
                }
            }
        })

        // listener for swipe refresh
        swipeRefresh.setOnRefreshListener {
            // resetting the pagination state
            scrollListener?.resetState()
            // resetting the currentPage
            viewModel.currentPage=1
            // fetch data  from the first page
            viewModel.startJob()
        }
    }


}

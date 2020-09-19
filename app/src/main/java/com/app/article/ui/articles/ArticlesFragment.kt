package com.app.article.ui.articles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.app.article.R
import com.app.article.databinding.FragmentArticlesBinding
import com.app.article.utils.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ArticlesFragment :  DaggerFragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val viewModel: ArticlesViewModel by viewModels {
        providerFactory
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentArticlesBinding>(
            inflater,
            R.layout.fragment_articles,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}

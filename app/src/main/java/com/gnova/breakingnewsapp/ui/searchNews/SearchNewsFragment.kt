package com.gnova.breakingnewsapp.ui.searchNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnova.breakingnewsapp.App
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.ViewModelFactory
import com.gnova.breakingnewsapp.databinding.FragmentSavedNewsBinding
import com.gnova.breakingnewsapp.databinding.FragmentSearchNewsBinding
import com.gnova.breakingnewsapp.ui.NewsAdapter
import com.gnova.breakingnewsapp.ui.breakingNews.BreakingNewsViewModel
import com.gnova.breakingnewsapp.ui.searchNews.SearchNewsFragment_MembersInjector.create
import com.gnova.domain.models.Article
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<SearchNewsViewModel>
    private lateinit var viewModel: SearchNewsViewModel
    private val adapter: NewsAdapter by lazy {
        NewsAdapter(
                NewsAdapter.OnClickListener{
                    onItemClicked(it)
                }
        )
    }

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        val binding = FragmentSearchNewsBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchNewsViewModel::class.java)

        setupRecyclerView()

        //observeViewState()
    }

    private fun searchNews(){
        binding.searchNewsEt.addTextChangedListener { editable ->
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }


    private fun showNews(articles: List<Article>) {
        Log.d("TAG", "showNews")
        adapter.submitList(articles)
    }

    private fun setupRecyclerView() {
        Log.d("TAG", "setupRecyclerView")
        binding.searchNewsRv.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
        }
    }

    private fun onItemClicked(article: Article) {
        findNavController().navigate(
            SearchNewsFragmentDirections.actionSearchNewsFragmentToArticleFragment(article)
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
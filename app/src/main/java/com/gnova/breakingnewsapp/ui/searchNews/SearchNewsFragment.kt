package com.gnova.breakingnewsapp.ui.searchNews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnova.breakingnewsapp.App
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.ViewModelFactory
import com.gnova.breakingnewsapp.databinding.FragmentSearchNewsBinding
import com.gnova.breakingnewsapp.ui.NewsAdapter
import com.gnova.breakingnewsapp.ui.ViewState
import com.gnova.breakingnewsapp.ui.ViewState.*
import com.gnova.domain.models.Article

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

        searchNews()

        observeViewState()
    }

    private fun searchNews(){
        binding.searchBtn.setOnClickListener {
            if(binding.searchNewsEt.text.toString().isNotEmpty()) {
                viewModel.onSearchButtonClick(binding.searchNewsEt.text.toString());
            }
        }
    }


    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> {
                    Log.d("TAG", "LOADING")
                    binding.statusImageIv.visibility = View.VISIBLE
                    binding.statusImageIv.setImageResource(R.drawable.loading_animation)
                }
                is Error -> {
                    Log.d("TAG", "ERROR BREAKING NEWS FRAGMENT")
                    binding.statusImageIv.visibility = View.VISIBLE
                    binding.statusImageIv.setImageResource(R.drawable.ic_connection_error)
                }
                is Presenting -> {
                    binding.statusImageIv.visibility = View.GONE
                    Log.d("TAG", "PRESENTING!!!")
                    showNews(it.results)
                }
            }
        })
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
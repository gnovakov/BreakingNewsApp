package com.gnova.breakingnewsapp.ui.breakingNews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnova.breakingnewsapp.App
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.ViewModelFactory
import com.gnova.breakingnewsapp.databinding.FragmentBreakingNewsBinding
import com.gnova.breakingnewsapp.ui.NewsAdapter
import com.gnova.domain.models.Article
import javax.inject.Inject

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<BreakingNewsViewModel>
    private lateinit var viewModel: BreakingNewsViewModel
    private val adapter: NewsAdapter by lazy {
        NewsAdapter(
                NewsAdapter.OnClickListener{
                onItemClicked(it)
            }
        )
    }

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        val binding = FragmentBreakingNewsBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this, viewModelFactory).get(BreakingNewsViewModel::class.java)

        setupRecyclerView()

        viewModel.onViewLoaded()

        observeViewState()

    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BreakingNewsViewState.Loading -> {
                    Log.d("TAG", "LOADING")
                    binding.statusImageIv.visibility = View.VISIBLE
                    binding.statusImageIv.setImageResource(R.drawable.loading_animation)
                }
                is BreakingNewsViewState.Error -> {
                    Log.d("TAG", "ERROR BREAKING NEWS FRAGMENT")
                    binding.statusImageIv.visibility = View.VISIBLE
                    binding.statusImageIv.setImageResource(R.drawable.ic_connection_error)
                }
                is BreakingNewsViewState.Presenting -> {
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
        binding.breakingNewsRv.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
        }
    }

    private fun onItemClicked(article: Article) {
        findNavController().navigate(
            BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(article)
        )
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
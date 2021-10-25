package com.gnova.breakingnewsapp.ui.article

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.gnova.breakingnewsapp.App
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.ViewModelFactory
import com.gnova.breakingnewsapp.databinding.FragmentArticleBinding
import javax.inject.Inject

class ArticleFragment : Fragment(R.layout.fragment_article) {


    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<ArticleViewModel>
    private lateinit var viewModel: ArticleViewModel

    val args: ArticleFragmentArgs by navArgs()

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).component.inject(this)
        val binding = FragmentArticleBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticleViewModel::class.java)

        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {
            viewModel.onArticleSaveClicked(article)
        }

    }




    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
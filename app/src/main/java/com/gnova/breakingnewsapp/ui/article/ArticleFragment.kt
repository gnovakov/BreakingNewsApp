package com.gnova.breakingnewsapp.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.databinding.FragmentArticleBinding
import com.gnova.breakingnewsapp.databinding.FragmentBreakingNewsBinding
import com.gnova.breakingnewsapp.databinding.FragmentSavedNewsBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {


    val args: ArticleFragmentArgs by navArgs()

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArticleBinding.bind(view)
        _binding = binding

        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

    }




    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
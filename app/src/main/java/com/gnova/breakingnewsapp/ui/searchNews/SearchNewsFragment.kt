package com.gnova.breakingnewsapp.ui.searchNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.databinding.FragmentSavedNewsBinding
import com.gnova.breakingnewsapp.databinding.FragmentSearchNewsBinding

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchNewsBinding.bind(view)
        _binding = binding
    }





    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
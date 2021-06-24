package com.gnova.breakingnewsapp.ui.breakingNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.ViewModelFactory
import com.gnova.breakingnewsapp.databinding.FragmentBreakingNewsBinding
import com.gnova.breakingnewsapp.databinding.FragmentSavedNewsBinding
import javax.inject.Inject

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<BreakingNewsViewModel>
    private lateinit var viewModel: BreakingNewsViewModel

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBreakingNewsBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this, viewModelFactory).get(BreakingNewsViewModel::class.java)

        //setupRecyclerView()

        //viewModel.onViewLoaded()

       //observeviewState()
        //observeClick()

    }




    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
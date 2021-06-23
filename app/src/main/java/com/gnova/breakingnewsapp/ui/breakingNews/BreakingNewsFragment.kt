package com.gnova.breakingnewsapp.ui.breakingNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gnova.breakingnewsapp.ViewModelFactory
import com.gnova.breakingnewsapp.databinding.FragmentBreakingNewsBinding
import javax.inject.Inject

class BreakingNewsFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<BreakingNewsViewModel>
    private lateinit var viewModel: BreakingNewsViewModel

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this, viewModelFactory).get(BreakingNewsViewModel::class.java)

        //setupRecyclerView()

        viewModel.onViewLoaded()

       //observeviewState()
        //observeClick()

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
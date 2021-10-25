package com.gnova.breakingnewsapp.ui.savedNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnova.breakingnewsapp.App
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.databinding.FragmentSavedNewsBinding

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).component.inject(this)
        val binding = FragmentSavedNewsBinding.bind(view)
        _binding = binding
    }





    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

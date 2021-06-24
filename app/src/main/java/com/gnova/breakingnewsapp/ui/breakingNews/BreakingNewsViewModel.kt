package com.gnova.breakingnewsapp.ui.breakingNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gnova.data.repositories.NewsRepoImpl
import javax.inject.Inject

class BreakingNewsViewModel @Inject constructor(
        private val newsRepoImpl: NewsRepoImpl
        ) : ViewModel() {

    // View State
    private val _viewState = MutableLiveData<BreakingNewsViewState>()
    val viewState: LiveData<BreakingNewsViewState>
        get() = _viewState


    fun onViewLoaded() {
        //getBreakingNews()
    }

}
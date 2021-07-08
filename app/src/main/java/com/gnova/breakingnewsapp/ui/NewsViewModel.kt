package com.gnova.breakingnewsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gnova.breakingnewsapp.ui.ViewState
import com.gnova.data.repositories.NewsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class NewsViewModel @Inject constructor(
        private val newsRepoImpl: NewsRepoImpl
        ) : ViewModel() {

    // View State
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState


    fun onViewLoaded() {
        getBreakingNews("gb", 1)
    }


    fun onSearchButtonClick(searchQuery: String) {
        searchNews(searchQuery, 1)
    }


    private fun getBreakingNews(country: String, page: Int) {

        _viewState.value = ViewState.Loading
        add(
            newsRepoImpl.getBreakingNews(country, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _viewState.value = ViewState.Presenting(it)
                }, {
                    //RxJavaPlugins.onError(it)
                    Log.d("TAG", "ERROR HOME VM $it")
                    _viewState.value = ViewState.Error
                })
        )
    }

    private fun searchNews(searchQuery: String, page: Int) {

        _viewState.value = ViewState.Loading
        add(
            newsRepoImpl.searchNews(searchQuery, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _viewState.value = ViewState.Presenting(it)
                }, {
                    Log.d("TAG", "ERROR HOME VM $it")
                    //onError(it)
                    _viewState.value = ViewState.Error
                })
        )
    }


    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
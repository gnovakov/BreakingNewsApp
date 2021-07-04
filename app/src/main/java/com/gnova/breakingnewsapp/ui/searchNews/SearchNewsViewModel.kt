package com.gnova.breakingnewsapp.ui.searchNews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gnova.data.repositories.NewsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SearchNewsViewModel @Inject constructor(
        private val newsRepoImpl: NewsRepoImpl
        ) : ViewModel() {

    // View State
    private val _viewState = MutableLiveData<SearchNewsViewState>()
    val viewState: LiveData<SearchNewsViewState>
        get() = _viewState


    fun onSearchButtonClick(search: String) {
        searchNews(search, 1)
    }


    private fun searchNews(search: String, page: Int) {

        _viewState.value = SearchNewsViewState.Loading
        add(
            newsRepoImpl.searchNews(search, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _viewState.value = SearchNewsViewState.Presenting(it)
                }, {
                    RxJavaPlugins.onError(it)
                    Log.d("TAG", "ERROR HOME VM")
                    _viewState.value = SearchNewsViewState.Error
                }
                )
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
package com.gnova.breakingnewsapp.ui.searchNews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gnova.breakingnewsapp.ui.ViewState
import com.gnova.breakingnewsapp.ui.ViewState.*
import com.gnova.data.repositories.NewsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.plugins.RxJavaPlugins.onError
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SearchNewsViewModel @Inject constructor(
        private val newsRepoImpl: NewsRepoImpl
        ) : ViewModel() {

    // View State
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState


    fun onSearchButtonClick(searchQuery: String) {
        searchNews(searchQuery, 1)
    }


    private fun searchNews(searchQuery: String, page: Int) {

        _viewState.value = Loading
        add(
            newsRepoImpl.searchNews(searchQuery, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _viewState.value = Presenting(it)
                }, {
                    Log.d("TAG", "ERROR HOME VM $it")
                    //onError(it)
                    _viewState.value = Error
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
package com.gnova.breakingnewsapp.ui.breakingNews

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

open class BreakingNewsViewModel @Inject constructor(
        private val newsRepoImpl: NewsRepoImpl
        ) : ViewModel() {

    // View State
    private val _viewState = MutableLiveData<BreakingNewsViewState>()
    val viewState: LiveData<BreakingNewsViewState>
        get() = _viewState


    fun onViewLoaded() {
        getBreakingNews("gb", 1)
    }


    private fun getBreakingNews(country: String, page: Int) {

        _viewState.value = BreakingNewsViewState.Loading
        add(
            newsRepoImpl.getBreakingNews(country, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _viewState.value = BreakingNewsViewState.Presenting(it)
                }, {
                    RxJavaPlugins.onError(it)
                    Log.d("TAG", "ERROR HOME VM")
                    _viewState.value = BreakingNewsViewState.Error
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
package com.gnova.breakingnewsapp.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gnova.breakingnewsapp.ui.ViewState
import com.gnova.data.models.ArticleDTO
import com.gnova.data.repositories.NewsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class ArticleViewModel @Inject constructor(
        private val newsRepoImpl: NewsRepoImpl
        ) : ViewModel() {

    // View State
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    fun onArticleSaveClicked(articleDTO: ArticleDTO) {
        articleDTO?.let {
            insertUpdate(it) }
    }

    private fun insertUpdate(articleDTO: ArticleDTO) {
        add(newsRepoImpl.insertUpdate(articleDTO)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                        }, {
                            it
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
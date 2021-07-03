package com.gnova.breakingnewsapp.ui.searchNews

import com.gnova.domain.models.Article


sealed class SearchNewsViewState {

    data class Presenting( val results: List<Article>) : SearchNewsViewState()

    object Error : SearchNewsViewState()

    object Loading : SearchNewsViewState()
}
package com.gnova.breakingnewsapp.ui.breakingNews

import com.gnova.domain.models.Article


sealed class BreakingNewsViewState {

    data class Presenting( val results: List<Article>) : BreakingNewsViewState()

    object Error : BreakingNewsViewState()

    object Loading : BreakingNewsViewState()
}
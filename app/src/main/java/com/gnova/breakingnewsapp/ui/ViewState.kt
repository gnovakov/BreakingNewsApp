package com.gnova.breakingnewsapp.ui

import com.gnova.domain.models.Article


sealed class ViewState {

    data class Presenting( val results: List<Article>) : ViewState()

    object Error : ViewState()

    object Loading : ViewState()
}
package com.gnova.breakingnewsapp.di

import android.content.Context
import com.example.popularmovies_kotlin.di.modules.ApiModule
import com.example.popularmovies_kotlin.di.modules.AppModule
import com.gnova.breakingnewsapp.MainActivity
import com.gnova.breakingnewsapp.ui.article.ArticleFragment
import com.gnova.breakingnewsapp.ui.breakingNews.BreakingNewsFragment
import com.gnova.breakingnewsapp.ui.savedNews.SavedNewsFragment
import com.gnova.breakingnewsapp.ui.searchNews.SearchNewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: BreakingNewsFragment)
    fun inject(fragment: SavedNewsFragment)
    fun inject(fragment: SearchNewsFragment)
    fun inject(fragment: ArticleFragment)

}
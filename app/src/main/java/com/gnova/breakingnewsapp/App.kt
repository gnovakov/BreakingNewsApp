package com.gnova.breakingnewsapp

import android.app.Application
import com.gnova.breakingnewsapp.di.AppComponent
import com.gnova.breakingnewsapp.di.DaggerAppComponent
import com.gnova.breakingnewsapp.di.modules.RoomModule

class App : Application() {

    val component: AppComponent = DaggerAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .build()
}
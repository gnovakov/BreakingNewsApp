package com.gnova.breakingnewsapp

import android.app.Application
import com.gnova.breakingnewsapp.di.AppComponent
import com.gnova.breakingnewsapp.di.DaggerAppComponent
import com.gnova.breakingnewsapp.di.modules.RoomModule

open class App : Application() {

//    // Instance of the AppComponent that will be used by all the Activities in the project
//    val appComponent: AppComponent by lazy {
//        initializeComponent()
//    }
//
//    open fun initializeComponent(): AppComponent {
//        // Creates an instance of AppComponent using its Factory constructor
//        // We pass the applicationContext that will be used as Context in the graph
//        return DaggerAppComponent.factory().create(applicationContext)
//    }

    val component: AppComponent = DaggerAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .build()
}
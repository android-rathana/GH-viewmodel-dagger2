package com.example.ratha.app

import android.app.Application
import android.content.Context
import com.example.ratha.app.di.component.ApplicationComponent
import com.example.ratha.app.di.component.DaggerApplicationComponent

class GHApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }

    companion object {
        fun getComponent(context: Context): ApplicationComponent =
                (context.applicationContext as GHApplication).applicationComponent
    }

}
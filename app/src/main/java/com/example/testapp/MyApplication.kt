package com.example.testapp

import android.app.Application
import com.example.testapp.dagger.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any> // Note: <Any> in Kotlin, <Object> in Java

    override fun onCreate() {
        super.onCreate()
        // Initialize Dagger component which will inject dispatchingAndroidInjector
        DaggerApplicationComponent.builder()
            .create(this)
            .build()
            .inject(this) // Make sure your ApplicationComponent can inject into MyApplication
    }

    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
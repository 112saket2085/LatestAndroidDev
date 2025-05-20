package com.example.testapp.dagger

import com.example.testapp.MyApplication
import com.example.testapp.dagger.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Builder
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    ViewModelModule::class,
])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: MyApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MyApplication)
}
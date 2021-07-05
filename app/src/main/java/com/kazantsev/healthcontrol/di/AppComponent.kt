package com.kazantsev.healthcontrol.di

import com.kazantsev.healthcontrol.ui.HealthFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: HealthFragment)


}
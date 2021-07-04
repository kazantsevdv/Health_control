package com.kazantsev.healthcontrol.di

import com.kazantsev.healthcontrol.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {


    @Singleton
    @Provides
    fun app(): App {
        return app
    }

}
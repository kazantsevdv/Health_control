package com.kazantsev.healthcontrol.di

import com.kazantsev.healthcontrol.App
import com.kazantsev.healthcontrol.repo.FirestoreRepository
import com.kazantsev.healthcontrol.repo.FirestoreRepositoryImp
import com.kazantsev.healthcontrol.util.ConverterModel
import com.kazantsev.healthcontrol.util.ConverterModelImp
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

    @Provides
    @Singleton
    fun provideRepo(): FirestoreRepository = FirestoreRepositoryImp()

    @Provides
    @Singleton
    fun provideConverterModel(): ConverterModel = ConverterModelImp()

}
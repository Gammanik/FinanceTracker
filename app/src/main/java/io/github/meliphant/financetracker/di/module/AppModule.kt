package io.github.meliphant.financetracker.di.module

import dagger.Module
import dagger.Provides
import io.github.meliphant.financetracker.MyApp
import javax.inject.Singleton

@Module class AppModule(val app: MyApp) {
    @Provides @Singleton fun provideApp() = app
}
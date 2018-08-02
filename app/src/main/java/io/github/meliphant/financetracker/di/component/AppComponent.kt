package io.github.meliphant.financetracker.di.component

import dagger.Component
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.di.module.AppModule
import javax.inject.Singleton


@Singleton @Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: MyApp)
}

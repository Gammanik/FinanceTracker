package io.github.meliphant.financetracker

import android.app.Application
import io.github.meliphant.financetracker.di.component.AppComponent
import io.github.meliphant.financetracker.di.component.DaggerAppComponent
import io.github.meliphant.financetracker.di.module.AppModule


class MyApp: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
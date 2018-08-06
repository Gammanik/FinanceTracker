package io.github.meliphant.financetracker.di

import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.di.component.AppComponent

val AppCompatActivity.component: AppComponent
    get() = (application as MyApp).component

val FragmentActivity.component: AppComponent
    get() = (application as MyApp).component
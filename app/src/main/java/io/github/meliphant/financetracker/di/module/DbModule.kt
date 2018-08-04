package io.github.meliphant.financetracker.di.module

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.data.AppDb
import javax.inject.Singleton


@Module class DbModule {

    @Provides @Singleton
    fun provideDb(app: MyApp): AppDb =
            Room.databaseBuilder(app, AppDb::class.java, "mobileDb").build()

    @Provides @Singleton
    fun provideOperationDao(db: AppDb) = db.operationDao()

    @Provides @Singleton
    fun provideWalletDao(db: AppDb) = db.walletDao()
}
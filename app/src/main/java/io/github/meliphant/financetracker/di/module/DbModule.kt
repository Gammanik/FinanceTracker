package io.github.meliphant.financetracker.di.module

import dagger.Module
import dagger.Provides
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.data.AppDb
import javax.inject.Singleton


@Module class DbModule {

    @Provides @Singleton
    fun provideDb(app: MyApp): AppDb = AppDb.getInstance(app)

    @Provides @Singleton
    fun provideOperationDao(db: AppDb) = db.operationDao()

    @Provides @Singleton
    fun provideWalletDao(db: AppDb) = db.walletDao()

    @Provides @Singleton
    fun provideCategoryDao(db: AppDb) = db.categoryDao()

    @Provides @Singleton
    fun provideWalletOperationDao(db: AppDb) = db.walletOperationDao()
}
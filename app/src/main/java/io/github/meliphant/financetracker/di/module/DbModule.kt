package io.github.meliphant.financetracker.di.module

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import dagger.Module
import dagger.Provides
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.data.AppDb
import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import kotlinx.coroutines.experimental.launch
import javax.inject.Singleton


@Module class DbModule {

    @Provides @Singleton
    fun provideDb(app: MyApp): AppDb = AppDb.getInstance(app)

    @Provides @Singleton
    fun provideOperationDao(db: AppDb) = db.operationDao()

    @Provides @Singleton
    fun provideWalletDao(db: AppDb) = db.walletDao()
}
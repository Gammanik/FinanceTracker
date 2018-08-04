package io.github.meliphant.financetracker.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.github.meliphant.financetracker.data.model.*
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.dao.WalletDao

@Database(entities = [(IdleOperation::class), (Wallet::class), (MyCategory::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDb: RoomDatabase() {
    abstract fun operationDao(): OperationDao
    abstract fun walletDao(): WalletDao
}
package io.github.meliphant.financetracker.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.github.meliphant.financetracker.data.model.Converters
import io.github.meliphant.financetracker.data.model.OperationDao
import io.github.meliphant.financetracker.data.model.idleOperation

@Database(entities = [(idleOperation::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDb: RoomDatabase() {
    abstract fun operationDao(): OperationDao
}
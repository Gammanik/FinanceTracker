package io.github.meliphant.financetracker.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.OperationDao

@Database(entities = [(Operation::class)], version = 1)
abstract class AppDb: RoomDatabase() {
    abstract fun operationDao(): OperationDao
}
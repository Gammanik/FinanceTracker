package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface OperationDao {

    @Query("SELECT * FROM operation")
    fun getAll(): List<Operation>

    @Query("DELETE FROM operation")
    fun nukeTable()

    @Insert
    fun saveOperation(op: Operation)


}
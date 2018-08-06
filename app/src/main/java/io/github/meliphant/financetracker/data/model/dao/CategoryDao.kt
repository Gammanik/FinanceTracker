package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.meliphant.financetracker.data.model.MyCategory

@Dao
interface CategoryDao {

    @Insert
    fun saveCategory(myCategory: MyCategory)

    @Query("SELECT * FROM mycategory")
    fun getAllCategories(): List<MyCategory>
}
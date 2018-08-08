package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.utils.CategorySpend

@Dao
interface CategoryDao {

    @Insert
    fun saveCategory(myCategory: MyCategory)

    @Query("SELECT * FROM mycategory")
    fun getAllCategories(): List<MyCategory>

    //todo: is there a way to do it for all categories through SQL and return List<CategorySpend> then?
//    https://www.w3resource.com/sql/aggregate-functions/sum-with-group-by.php
//    @Query("""SELECT MyCategory.*, SUM(idleoperation.amountOp_amount) amount
//        FROM idleoperation
//        INNER JOIN mycategory ON IdleOperation.categoryId = MyCategory.categoryId
//        WHERE walletId=:walletId GROUP BY MyCategory.categoryId""")
//    fun getCategorySpent(walletId: Int): List<CategorySpend>
}
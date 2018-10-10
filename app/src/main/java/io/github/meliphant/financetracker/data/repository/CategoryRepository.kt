package io.github.meliphant.financetracker.data.repository

import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.dao.CategoryDao
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.utils.CategorySpend
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val operationDao: OperationDao, private val categoryDao: CategoryDao) {

    fun getAllCategories(): List<MyCategory> {
        return categoryDao.getAllCategories()
    }

    fun getAllCategoriesSpend(walletId: Int): List<CategorySpend> {
        return categoryDao.getCategorySpent(walletId)
    }
}
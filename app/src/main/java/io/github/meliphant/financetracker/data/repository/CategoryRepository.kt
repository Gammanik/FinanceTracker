package io.github.meliphant.financetracker.data.repository

import io.github.meliphant.financetracker.data.model.dao.CategoryDao
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val operationDao: OperationDao, private val categoryDao: CategoryDao) {

    fun getAllCategoriesSpend() {
        val categoriesList = categoryDao.getAllCategories()
        operationDao.getAll()
    }
}
package io.github.meliphant.financetracker.data.repository


import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.utils.MyCurrency

class OperationRepository(val operationDao: OperationDao) {

    fun getAllTransactions(): List<Operation> {
        return operationDao.getAll()
    }

    fun getTransactions(walletId: Int): List<Operation> {
        return operationDao.getByWalletId(walletId)
    }

}
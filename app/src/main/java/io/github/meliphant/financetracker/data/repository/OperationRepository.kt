package io.github.meliphant.financetracker.data.repository


import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class OperationRepository @Inject constructor(private val operationDao: OperationDao) {

    //todo: change for Operation with mapper
    fun saveOperation(idleOP: IdleOperation) {
        operationDao.saveOperation(idleOP)
    }

    fun getAllOperations(): List<Operation> {
        return operationDao.getAll()
    }

    fun getOperations(walletId: Int): List<Operation> {
        return operationDao.getByWalletId(walletId)
    }

}
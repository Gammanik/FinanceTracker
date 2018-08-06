package io.github.meliphant.financetracker.data.repository


import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.dao.WalletOperationDao
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class OperationRepository @Inject constructor(private val operationDao: OperationDao, private val walletOperationDao: WalletOperationDao) {

    fun saveOperation(op: Operation) {
        val opWithUpdatedWallet = changeWalletBalance(op)
        val idleOp = OperationMapper.mapOperationToIdleOperation(opWithUpdatedWallet)

        walletOperationDao.insertOperationAndUpdateWallet(idleOp, op.wallet)
    }

    fun getAllOperations(): List<Operation> {
        return operationDao.getAll()
    }

    fun getOperations(walletId: Int): List<Operation> {
        return operationDao.getByWalletId(walletId)
    }

}

private fun changeWalletBalance(op: Operation): Operation {
    if (op.type == OperationType.INCOME)
        op.wallet.money.amount += op.amountOperationCurrency.amount

    if (op.type == OperationType.OUTCOME)
        op.wallet.money.amount -= op.amountOperationCurrency.amount

    return op
}

object OperationMapper {
    fun mapOperationToIdleOperation(operation: Operation) : IdleOperation {
        val id = operation.operationId
        val opType = operation.type
        val comment = operation.comment

        val walletId = operation.wallet.walletId
        val amountCurrencyMain = operation.amountMainCurrency
        val amountCurrencyOperation = operation.amountOperationCurrency
        val date = operation.datetime
        val categoryId = operation.category.categoryId

        return IdleOperation(id, opType, comment, amountCurrencyOperation, amountCurrencyMain, walletId, categoryId, date)
    }
}
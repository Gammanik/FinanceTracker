package io.github.meliphant.financetracker.data.repository


import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.dao.WalletOperationDao
import io.github.meliphant.financetracker.data.model.utils.OperationType
import java.util.*
import javax.inject.Inject

class OperationRepository @Inject constructor(private val operationDao: OperationDao, private val walletOperationDao: WalletOperationDao) {

    private val SECONDS_IN_DAY = 864000

    fun saveOperation(op: Operation) {
        val opWithUpdatedWallet = changeWalletBalance(op)
        val idleOp = OperationMapper.mapOperationToIdleOperation(opWithUpdatedWallet)

        walletOperationDao.insertOperationAndUpdateWallet(idleOp, op.wallet)
    }

    fun getAllOperations(): List<Operation> {
        handlePeriodicOperations()
        return operationDao.getAll()
    }

    fun getOperationsByWalletId(walletId: Int): List<Operation> {
        handlePeriodicOperations()
        return operationDao.getByWalletId(walletId)
    }

    fun getAllPeriodicOperations(): List<Operation> {
        return operationDao.getAllPeriodic()
    }

    private fun changeWalletBalance(op: Operation): Operation {
        if (op.type == OperationType.INCOME)
            op.wallet.money.amount += op.amountOperationCurrency.amount

        if (op.type == OperationType.OUTCOME)
            op.wallet.money.amount -= op.amountOperationCurrency.amount

        return op
    }

    private fun handlePeriodicOperations() {
        val now = Date()
        val periodicOperations = operationDao.getAllPeriodic()

        for (periodicOperation in periodicOperations) {
            if (periodicOperation.datetime <= now) {

                //todo: fix periodic op datetime
                var count = 0L
                if (periodicOperation.periodSeconds > 0)  //checking just in case
                    count = (now.time - periodicOperation.datetime.time) / (periodicOperation.periodSeconds * SECONDS_IN_DAY)

                for (i in 0 until count) {

                    val normOp = fromPeriodicToNormalOperation(periodicOperation)
                    saveOperation(normOp)
                    periodicOperation.datetime = Date(periodicOperation.datetime.time + SECONDS_IN_DAY * periodicOperation.periodSeconds * i)
                }

                operationDao.updateOperation(OperationMapper.mapOperationToIdleOperation(periodicOperation))
            }
        }
    }


}

fun fromPeriodicToNormalOperation(periodicOp: Operation): Operation {
    //doing this just for autogenerating operationId
    return Operation(
            type = periodicOp.type,
            comment = periodicOp.comment,
            amountOperationCurrency = periodicOp.amountOperationCurrency,
            amountMainCurrency = periodicOp.amountMainCurrency,
            wallet = periodicOp.wallet,
            category = periodicOp.category,
            datetime = periodicOp.datetime,
            periodSeconds = 0
    )
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
        val periodSeconds = operation.periodSeconds

        return IdleOperation(id, opType, comment, amountCurrencyOperation, amountCurrencyMain, walletId, categoryId, date, periodSeconds)
    }
}
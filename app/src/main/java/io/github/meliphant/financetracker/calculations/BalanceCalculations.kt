package io.github.meliphant.financetracker.calculations

import io.github.meliphant.financetracker.data.OperationType
import io.github.meliphant.financetracker.ui.transaction.NewTransactionDialog

object BalanceCalculations {

    private val currencyRate = 63.00

    /** Пересчет валюты происходит для total суммы, так как каждое новое добаленное поле
    будет сразу единовременно конвертироваться в дефолтную валюту - рубли и храниться в рублях.
     */

    fun countBalanceForDataSampleRub(operationTypeIndex: Int): Double {
        val totalBalance = getTotalBalance(operationTypeIndex)
        return getDefaultCurrency(totalBalance, currencyRate)
    }

    fun countBalanceForDataSampleUsd(operationTypeIndex: Int): Double {
        return getTotalBalance(operationTypeIndex)
    }

    private fun getTotalBalance(operationTypeIndex: Int): Double {
        var totalBalance = 0.00
        for (operationItem in NewTransactionDialog.transactionList) {
            totalBalance += operationItem.amount * itemAmountWithSign(operationTypeIndex)
        }
        return totalBalance
    }

    private fun itemAmountWithSign(operationTypeIndex: Int): Byte {
        return if (operationTypeIndex == OperationType.INCOME.position) 1
        else -1
    }

    private fun getDefaultCurrency(totalBalance: Double, currencyRate: Double): Double {
        return totalBalance * currencyRate
    }
}

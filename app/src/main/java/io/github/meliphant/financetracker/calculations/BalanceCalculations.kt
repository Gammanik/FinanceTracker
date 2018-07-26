package io.github.meliphant.financetracker.calculations

import io.github.meliphant.financetracker.data.Currency
import io.github.meliphant.financetracker.data.DataOperation
import io.github.meliphant.financetracker.data.OperationType

object BalanceCalculations {

    private val financeList = ArrayList<DataOperation>()
    private val currencyRate = 63.00

    init {
        financeList.add(DataOperation(1000.0, OperationType.INCOME.toString(),
                Currency.USD.toString()))
        financeList.add(DataOperation(500.00, OperationType.OUTCOME.toString(),
                Currency.USD.toString()))
        financeList.add(DataOperation(500.00, OperationType.INCOME.toString(),
                Currency.USD.toString()))
        financeList.add(DataOperation(3000.00, OperationType.OUTCOME.toString(),
                Currency.RUB.toString()))
    }

    /** Пересчет валюты происходит для total суммы, так как каждое новое добаленное поле
    будет сразу единовременно конвертироваться в дефолтную валюту - рубли и храниться в рублях.
     */
    fun countBalanceForDataSampleRub(): Double {
        val totalBalance = getTotalBalance()
        return getDefaultCurrency(totalBalance, currencyRate)
    }

    fun countBalanceForDataSampleUsd(): Double {
        return getTotalBalance()
    }

    private fun getTotalBalance(): Double {
        var totalBalance = 0.00
        for (operationItem in financeList) {
            totalBalance += itemAmountWithSign(operationItem)
        }
        return totalBalance
    }

    private fun itemAmountWithSign(operationItem: DataOperation): Double {
        return if (operationItem.operationType == OperationType.INCOME.toString())
            operationItem.amount
        else -operationItem.amount
    }

    private fun getDefaultCurrency(totalBalance: Double, currencyRate: Double): Double {
        return totalBalance * currencyRate
    }
}

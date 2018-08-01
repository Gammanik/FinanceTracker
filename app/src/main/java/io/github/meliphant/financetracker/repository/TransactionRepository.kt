package io.github.meliphant.financetracker.repository

import io.github.meliphant.financetracker.repository.model.Transaction
import io.github.meliphant.financetracker.repository.model.Wallet
import io.github.meliphant.financetracker.repository.model.utils.MyCategory
import io.github.meliphant.financetracker.repository.model.utils.MyCurrency

class TransactionRepository {


    val wallet1 = Wallet(0, 750,"wallet1", MyCurrency.RUB, "wallet_icon_card")
    val transactionList = mutableListOf(Transaction("tr ", 750, MyCategory.Groceries, wallet1))

    init {

        for (i in 1..15) {
            transactionList.add(Transaction("tr $i", 750, MyCategory.Groceries, wallet1))
        }

    }

    fun getTransactions(walletId: Int): List<Transaction> {
        return transactionList.filter { it.wallet.id == walletId }
    }
}
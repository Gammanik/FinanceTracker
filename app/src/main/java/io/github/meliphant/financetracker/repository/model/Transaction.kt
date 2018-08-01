package io.github.meliphant.financetracker.repository.model

import io.github.meliphant.financetracker.repository.model.utils.MyCategory

data class Transaction(val name: String, val amount: Int, val category: MyCategory, val wallet: Wallet) {
}
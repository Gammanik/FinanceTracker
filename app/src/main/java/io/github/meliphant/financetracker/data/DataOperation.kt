package io.github.meliphant.financetracker.data

data class DataOperation(val amount: Double,
                         val operationType: String,
                         val currency: String,
                         val category: String,
                         val account: String)

package io.github.meliphant.financetracker.data

enum class Currency {
    USD, RUB
}

enum class OperationType(val position:Int) {
    INCOME(0), OUTCOME(1)
}

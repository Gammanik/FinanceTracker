package io.github.meliphant.financetracker.data.model

import io.github.meliphant.financetracker.data.model.utils.MyCurrency

data class Money(var amount: Double,
                 val currency: MyCurrency)
package io.github.meliphant.financetracker.repository.model

import io.github.meliphant.financetracker.repository.model.utils.MyCurrency

data class Wallet(val id: Int, val amount: Int, val name: String, val currency: MyCurrency, val iconUrl: String) {

}
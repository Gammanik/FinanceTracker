package io.github.meliphant.financetracker.data.model

import io.github.meliphant.financetracker.data.model.utils.MyCurrency

data class Wallet(val id: Int, val name: String, val currency: MyCurrency, val iconUrl: String) {

}
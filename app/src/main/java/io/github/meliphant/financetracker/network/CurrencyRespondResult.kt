package io.github.meliphant.financetracker.network

import io.github.meliphant.financetracker.data.DataCurrencyRates

interface CurrencyRespondResult {

    fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates)
    fun onCurrencyErrorLoad()
}

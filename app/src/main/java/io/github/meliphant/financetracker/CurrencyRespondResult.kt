package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.DataCurrencyRates

interface CurrencyRespondResult {

    fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates)
    fun onCurrencyErrorLoad()
}

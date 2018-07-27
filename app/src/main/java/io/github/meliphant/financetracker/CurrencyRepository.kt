package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.DataCurrencyRates

class CurrencyRepository(val apiService: CurrencyApi) {

    fun getCurrencies(): io.reactivex.Observable<DataCurrencyRates> {
        return apiService.loadCurrencies()
    }
}


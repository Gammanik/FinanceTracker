package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.DataCurrencyRates
import io.reactivex.Observable

class CurrencyRepository(val apiService: CurrencyApi) {

    fun getCurrencies(): Observable<DataCurrencyRates> {
        return apiService.loadCurrencies()
    }
}

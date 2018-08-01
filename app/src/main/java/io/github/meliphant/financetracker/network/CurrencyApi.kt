package io.github.meliphant.financetracker.network

import io.github.meliphant.financetracker.repository.model.CURRENCY_PATH
import io.github.meliphant.financetracker.repository.model.DataCurrencyRates
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {

    @GET(CURRENCY_PATH)
    fun loadCurrencies(): Observable<DataCurrencyRates>
}

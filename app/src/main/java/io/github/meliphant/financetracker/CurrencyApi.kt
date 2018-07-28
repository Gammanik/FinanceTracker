package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.DataCurrencyRates
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {

    @GET(CURRENCY_PATH)
    fun loadCurrencies(): Observable<DataCurrencyRates>
}

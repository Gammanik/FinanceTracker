package io.github.meliphant.financetracker.network

import io.github.meliphant.financetracker.data.CURRENCY_HOST
import io.github.meliphant.financetracker.data.DataCurrencyRates
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object CurrencyModule {

    fun getCurrencies(): Observable<DataCurrencyRates> {
        val retrofit = Retrofit.Builder()
                .baseUrl(CURRENCY_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(CurrencyApi::class.java)
        return service.loadCurrencies()
    }
}

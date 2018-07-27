package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.DataExchangeRates

interface CurrencyApi {
    @retrofit2.http.GET("api/latest.json")
    fun search(@retrofit2.http.Query("app_id") app_id: String = "b8fcec806b2747439cf4eb597c456486"): io.reactivex.Observable<DataExchangeRates>

    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): io.github.meliphant.financetracker.CurrencyApi {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("https://openexchangerates.org/")
                    .build()

            return retrofit.create(io.github.meliphant.financetracker.CurrencyApi::class.java);
        }
    }
}

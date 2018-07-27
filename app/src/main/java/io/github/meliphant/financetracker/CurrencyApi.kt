package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.DataCurrencyRates
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET(CURRENCY_PATH)
    fun loadCurrencies(@Query(CURRENCY_QUERY) app_id: String = CURRENCY_API_KEY): Observable<DataCurrencyRates>

    companion object Factory {
        fun create(): CurrencyApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(CURRENCY_HOST)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(CurrencyApi::class.java);
        }
    }
}


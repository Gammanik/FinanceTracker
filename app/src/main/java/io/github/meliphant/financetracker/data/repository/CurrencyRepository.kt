package io.github.meliphant.financetracker.data.repository

import io.github.meliphant.financetracker.network.CurrencyModule
import io.github.meliphant.financetracker.network.CurrencyRespondResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CurrencyRepository {

    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private var currencyModule = CurrencyModule

    fun onCurrencyLoad(currencyRespondResult: CurrencyRespondResult) {

        if (compositeDisposable?.isDisposed == true) {
            compositeDisposable?.dispose()
            compositeDisposable = null
        }

        compositeDisposable?.add(
                currencyModule.getCurrencies()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ currencyRates ->
                            currencyRespondResult.onCurrencySuccessLoad(currencyRates)
                        }, { error ->
                            error.printStackTrace()
                            currencyRespondResult.onCurrencyErrorLoad()
                        })
        )
    }
}

package io.github.meliphant.financetracker

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CurrencyRepositoryProvider {

    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun onCurrencyLoad(currencyRespondResult: CurrencyRespondResult) {

        if (compositeDisposable?.isDisposed == true){
            compositeDisposable?.dispose()
            compositeDisposable = null
        }

        val repository = CurrencyRepository(CurrencyApi.Factory.create())
        compositeDisposable?.add(
                repository.getCurrencies()
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


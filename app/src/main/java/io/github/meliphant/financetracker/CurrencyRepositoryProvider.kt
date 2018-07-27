package io.github.meliphant.financetracker

object CurrencyRepositoryProvider {
    fun provideCurrencyRepository(): CurrencyRepository {
        return CurrencyRepository(CurrencyApi.Factory.create())
    }
}

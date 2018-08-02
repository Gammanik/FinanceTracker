package io.github.meliphant.financetracker.data.model.other

data class DataCurrencyRates(val timestamp: Long,
                             val base: String,
                             val rates: Map<String, Double>
)
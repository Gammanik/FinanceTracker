package io.github.meliphant.financetracker.repository.model

data class DataCurrencyRates(val timestamp: Long,
                             val base: String,
                             val rates: Map<String, Double>
)
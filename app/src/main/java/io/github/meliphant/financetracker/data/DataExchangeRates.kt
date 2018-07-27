package io.github.meliphant.financetracker.data

data class DataExchangeRates(val timestamp: Long,
                             val base: String,
                             val rates: Map<String, Double>
)
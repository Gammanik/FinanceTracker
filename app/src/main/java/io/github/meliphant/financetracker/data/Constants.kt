package io.github.meliphant.financetracker.data

import io.github.meliphant.financetracker.BuildConfig

const val CURRENCY_HOST = "https://openexchangerates.org/"
const val CURRENCY_API_KEY = BuildConfig.CurrencyApiKey
const val CURRENCY_PATH = "api/latest.json?app_id=${CURRENCY_API_KEY}"

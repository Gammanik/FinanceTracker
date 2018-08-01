package io.github.meliphant.financetracker.repository.model

import io.github.meliphant.financetracker.BuildConfig

const val CURRENCY_HOST = "https://openexchangerates.org/"
const val CURRENCY_PATH = "api/latest.json?app_id=${BuildConfig.CURRENCY_API_KEY}"

package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.BuildConfig

const val CURRENCY_HOST = "https://openexchangerates.org/"
const val CURRENCY_PATH = "api/latest.json?app_id=${BuildConfig.CURRENCY_API_KEY}"

const val ALL_WALLETS_ID = -1
const val ADD_WALLET_ID = -2

//const for fragments communication
enum class Keys {
    KEY_TRANSACTION_TYPE,
    KEY_WALLET_ID,
    KEY_TEMPLATE_ID
}
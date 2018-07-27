package io.github.meliphant.financetracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import io.github.meliphant.financetracker.data.DataCurrencyRates
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), CurrencyRespondResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        CurrencyRepositoryProvider().onCurrencyLoad(this)
    }

    override fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates) {
        Log.d("DataExchangeRates", "${currencyRates.rates}")
    }

    override fun onCurrencyErrorLoad() {
        settings.setText(R.string.msg_currency_error)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


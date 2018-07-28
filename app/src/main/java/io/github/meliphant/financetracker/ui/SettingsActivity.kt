package io.github.meliphant.financetracker.ui

import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import io.github.meliphant.financetracker.CurrencyRepository
import io.github.meliphant.financetracker.network.CurrencyRespondResult
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.DataCurrencyRates
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), CurrencyRespondResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        CurrencyRepository().onCurrencyLoad(this)
//        var settingsFragment = SettingsFragment()
//        fragmentManager.beginTransaction()
//                .replace(android.R.id.content, settingsFragment)
//                .commit()

        if (fragmentManager.findFragmentById(android.R.id.content) == null) {
            fragmentManager.beginTransaction()
                    .add(android.R.id.content, SettingsFragment())
                    .commit()
        }
    }

    override fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates) {
        Log.d("DataExchangeRates", "${currencyRates.rates}")
//        Toast.makeText(this, "DataExchangeRates" + "${currencyRates.rates}", Toast.LENGTH_SHORT).show()

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

    class SettingsFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings_preferences)
        }
    }
}


package io.github.meliphant.financetracker.ui

import android.R.attr.duration
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.design.widget.Snackbar
import android.util.Log
import io.github.meliphant.financetracker.CurrencyRepository
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.Currency
import io.github.meliphant.financetracker.data.DataCurrencyRates
import io.github.meliphant.financetracker.network.CurrencyRespondResult


class SettingsFragment : PreferenceFragment(),
        SharedPreferences.OnSharedPreferenceChangeListener, CurrencyRespondResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings_preferences)

        CurrencyRepository().onCurrencyLoad(this)

        setListPreferenceData()
    }

    private fun setListPreferenceData() {

        val listPreference: Preference = findPreference(getString(R.string.settings_currency_key))
        if (listPreference is ListPreference) {
            //TODO: Here should be loaded the list from currency rates response
            val entries = arrayOf<CharSequence>(Currency.USD.toString(), Currency.RUB.toString())
            val entryValues = arrayOf<CharSequence>("1", "2")
            listPreference.entries = entries
            listPreference.setDefaultValue("1")
            listPreference.entryValues = entryValues
            listPreference.setDialogTitle(R.string.settings_currency_title)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        if (key == getString(R.string.settings_currency_key)) {
            val connectionPref = findPreference(key)
            Log.d("SettingsActivity", "sharedPref" + connectionPref)
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCurrencySuccessLoad(currencyRates: DataCurrencyRates) {
        Log.d("DataExchangeRates", "${currencyRates.rates}")
    }

    override fun onCurrencyErrorLoad() {
        Snackbar.make(view, getString(R.string.msg_currency_error), duration).show()
    }
}

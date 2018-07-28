package io.github.meliphant.financetracker

import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import io.github.meliphant.financetracker.data.Currency


class SettingsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings_preferences)

        val listPreference: Preference = findPreference(getString(R.string.settings_currency_key))
        if (listPreference is ListPreference) setListPreferenceData(listPreference)

//        listPreference.onPreferenceClickListener = OnPreferenceClickListener {
//            setListPreferenceData(listPreference)
//            false
//        }
    }

    protected fun setListPreferenceData(lp: ListPreference) {
        val entries = arrayOf<CharSequence>(Currency.USD.toString(), Currency.RUB.toString())
        val entryValues = arrayOf<CharSequence>("1", "2")
        lp.entries = entries
        lp.setDefaultValue("1")
        lp.entryValues = entryValues
        lp.setDialogTitle(R.string.settings_currency_title)
    }
}

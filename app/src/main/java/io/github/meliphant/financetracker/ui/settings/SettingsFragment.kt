package io.github.meliphant.financetracker.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import io.github.meliphant.financetracker.R


class SettingsFragment : PreferenceFragment(),
        SharedPreferences.OnSharedPreferenceChangeListener {


    override fun onSharedPreferenceChanged(sp: SharedPreferences?, s: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings_preferences)
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
}

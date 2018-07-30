package io.github.meliphant.financetracker.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.meliphant.financetracker.R


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addSettingsList()
    }

    private fun addSettingsList() {
        if (fragmentManager.findFragmentById(android.R.id.content) == null) {
            fragmentManager.beginTransaction()
                    .add(android.R.id.content, SettingsFragment())
                    .commit()
        }
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

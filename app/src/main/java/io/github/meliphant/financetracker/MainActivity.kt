package io.github.meliphant.financetracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.github.meliphant.financetracker.calculations.BalanceCalculations.countBalanceForDataSampleRub
import io.github.meliphant.financetracker.calculations.BalanceCalculations.countBalanceForDataSampleUsd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        showBalance()

        fab.setOnClickListener { view ->
            val dialog = NewTransactionDialog()
            val ft = supportFragmentManager.beginTransaction()
            dialog.show(ft, NewTransactionDialog.TAG)
        }
    }

    private fun showBalance() {
        val totalBalanceInRub = countBalanceForDataSampleRub()
        val totalBalanceInUsd = countBalanceForDataSampleUsd()
        tv_currency_default.setText("$totalBalanceInRub RUB")
        tv_currency_selected.setText("$totalBalanceInUsd USD")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_about -> {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}

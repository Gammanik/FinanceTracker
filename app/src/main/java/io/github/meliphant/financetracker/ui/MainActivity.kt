package io.github.meliphant.financetracker.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.calculations.BalanceCalculations.countBalanceForDataSampleRub
import io.github.meliphant.financetracker.calculations.BalanceCalculations.countBalanceForDataSampleUsd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

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

        val dataAdapterAccounts = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, MainActivity.accountsList)
        dataAdapterAccounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_accounts.adapter = dataAdapterAccounts
        spinner_accounts.setOnItemSelectedListener(this)


        recycler_view.setHasFixedSize(true)
        val adapter = TransactionRecyclerAdapter(NewTransactionDialog.transactionList)
        recycler_view.setAdapter(adapter)
        recycler_view.setLayoutManager(LinearLayoutManager(this))
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(parent?.context, "Selected: $item", Toast.LENGTH_LONG).show()
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

    companion object {
        val TAG = "NewTransactionDialog"
        //TODO: add currencies API
        private val accountsList = listOf("All Accounts", "Cash", "Card")
    }
}

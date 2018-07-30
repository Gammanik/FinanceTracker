package io.github.meliphant.financetracker.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.ui.AboutActivity
import io.github.meliphant.financetracker.ui.transaction.NewTransactionDialog
import io.github.meliphant.financetracker.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        showInitialBalance()
        setDataForAccountsList()
        setTransactionListAdapter()
        setNewTransactionDialogFabOnClick()
    }

    private fun showInitialBalance() {
        tv_currency_default.text = resources.getString(R.string.zero_balance_default)
        tv_currency_custom.text = resources.getString(R.string.zero_balance_custom)
    }

    private fun setDataForAccountsList() {
        val accountsArray = resources.getStringArray(R.array.accounts)
        val accountsDataAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, accountsArray)
        accountsDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_accounts.adapter = accountsDataAdapter
        spinner_accounts.onItemSelectedListener
    }

    private fun setTransactionListAdapter() {
        recycler_view.setHasFixedSize(true)
        TransactionListAdapter(NewTransactionDialog.transactionList)
        recycler_view.adapter
        recycler_view.layoutManager
    }

    private fun setNewTransactionDialogFabOnClick() {
        fab.setOnClickListener { _ ->
            val ft = supportFragmentManager.beginTransaction()
            NewTransactionDialog().show(ft, NewTransactionDialog.TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            navigateToActivity(SettingsActivity::class.java)
            true
        }
        R.id.action_about -> {
            navigateToActivity(AboutActivity::class.java)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(parent?.context, "Selected: $item", Toast.LENGTH_LONG).show()
    }

    private fun navigateToActivity(className: Class<*>?) {
        val intent = Intent(this, className)
        startActivity(intent)
    }
}

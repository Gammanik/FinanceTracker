package io.github.meliphant.financetracker.ui.transaction

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.repository.model.DataOperation
import kotlinx.android.synthetic.main.dialog_layout.*

class NewTransactionDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TransactionDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setCurrencyAdapter()
        setCategoryAdapter()
        getSelectedAccountItem()

        handleSaveIcon()
        handleCloseIcon()
    }

    private fun setCurrencyAdapter() {
        //TODO: show list from shared preferences
        val dataAdapterCurrency = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, currencyList)
        dataAdapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_currency?.adapter = dataAdapterCurrency
        spinner_currency?.onItemSelectedListener
    }

    private fun setCategoryAdapter() {
        val categoryArray = resources.getStringArray(R.array.category)
        val dataAdapterCategory = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, categoryArray)
        dataAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_category?.adapter = dataAdapterCategory
        spinner_category?.onItemSelectedListener
    }

    private fun getSelectedAccountItem() {
        val accountsArray = resources.getStringArray(R.array.accounts)
        accountType = if (card.isChecked) accountsArray[1]
        else accountsArray[0]
    }

    private fun handleCloseIcon() {
        button_close?.setOnClickListener { _ -> dismiss() }
    }

    private fun handleSaveIcon() {
        button_save?.setOnClickListener {
            if (!amount.text.isEmpty()) {

                val numAmount = amount.text.toString().toDouble()
                val currency = spinner_currency.selectedItem.toString()
                val category = spinner_category.selectedItem.toString()
                getSelectedTransactionTypeItem()

                val newDataOperation = DataOperation(numAmount, transactionType,
                        currency, category, accountType)
                transactionList.add(newDataOperation)
                Log.d(TAG, "transactionList " + transactionList)
                dismiss()
            } else {
                Snackbar.make(view!!, getString(R.string.transaction_empty_amount_warning),
                        android.R.attr.duration).show()
            }
        }
    }

    private fun getSelectedTransactionTypeItem() {
        val transactionTypeArray = resources.getStringArray(R.array.type)
        if (income.isChecked) {
            transactionType = transactionTypeArray[0]
        } else {
            transactionType = transactionTypeArray[1]
            index = 1
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    companion object {
        // Temporary list with transactions. Use before adding data storage.
        val transactionList = mutableListOf<DataOperation>()
        var transactionType = ""
        var accountType = ""
        var index = 0
        const val TAG = "NewTransactionDialog"
        //TODO: add currencies API
        private val currencyList = listOf("RUB", "USD")
    }
}

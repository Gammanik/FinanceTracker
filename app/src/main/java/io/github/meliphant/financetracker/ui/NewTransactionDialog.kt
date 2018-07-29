package io.github.meliphant.financetracker.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.DataOperation
import kotlinx.android.synthetic.main.dialog_layout.*


class NewTransactionDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TransactionDialog)
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window.setLayout(width, height)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_close?.setOnClickListener { _ -> dismiss() }

        //TODO: show list from shared preferences
        val dataAdapterCurrency = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, currencyList)
        dataAdapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_currency?.adapter = dataAdapterCurrency
        spinner_currency?.setOnItemSelectedListener(this)

        val categoryArray = resources.getStringArray(R.array.category)
        val dataAdapterCategory = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, categoryArray)
        dataAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_category?.adapter = dataAdapterCategory
        spinner_category?.setOnItemSelectedListener(this)

        val accountsArray = resources.getStringArray(R.array.accounts)
        account_type?.setOnClickedButtonListener { button, _ ->

            if (button.text == resources.getText(R.string.card)) accountsArray[1]
            else accountType = accountsArray[0]
        }

        if (card.isChecked) accountType = accountsArray[1]
        else accountType = accountsArray[0]

        val transactionTypeArray = resources.getStringArray(R.array.type)
        transaction_type_group?.setOnClickedButtonListener { button, _ ->
            if (button.text == resources.getText(R.string.income)) transactionType = transactionTypeArray[0]
            else transactionType = transactionTypeArray[1]
        }

        if (income.isChecked) transactionType = transactionTypeArray[0]
        else transactionType = transactionTypeArray[1]

        button_save?.setOnClickListener {
            if (!amount.text.isEmpty()) {
                val numAmount = amount.text.toString().toDouble()
                val newDataOperation = DataOperation(numAmount, transactionType,
                        spinner_currency.selectedItem.toString(), spinner_category.selectedItem.toString(), accountType)
                transactionList.add(newDataOperation)
                dismiss()
            } else Snackbar.make(view!!, getString(R.string.transaction_empty_amount_warning), android.R.attr.duration).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val item = parent?.getItemAtPosition(position).toString()
//        Toast.makeText(parent?.context, "Selected: $item", Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = "NewTransactionDialog"
        //TODO: add currencies API
        private val currencyList = listOf<String>("RUB", "USD")

        // Temporary list with transactions. Use before adding data storage.
        val transactionList = mutableListOf<DataOperation>()
        var transactionType = ""
        var accountType = ""
    }
}

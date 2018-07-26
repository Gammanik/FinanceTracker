package io.github.meliphant.financetracker

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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

        dialog!!.getWindow().setLayout(width, height)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_close?.setOnClickListener { _ -> dismiss() }
        //TODO: save info when button_save clicked

        val dataAdapterCurrency = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, currencyList)
        dataAdapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_currency.adapter = dataAdapterCurrency
        spinner_currency.setOnItemSelectedListener(this)

        val dataAdapterCategory = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, categoryList)
        dataAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_category.adapter = dataAdapterCategory
        spinner_category.setOnItemSelectedListener(this)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(parent?.context, "Selected: $item", Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = "NewTransactionDialog"
        //TODO: add currencies API
        private val currencyList = listOf<String>("RUB", "USD")
        private val categoryList = listOf<String>("Еда & Напитки", "Магазины", "Транспорт",
                "Развлечения", "Дом", "Медиа","Инвестиции")
    }
}

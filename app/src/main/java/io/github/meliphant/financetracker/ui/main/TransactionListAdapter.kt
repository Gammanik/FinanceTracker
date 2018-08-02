package io.github.meliphant.financetracker.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.other.DataOperation

class TransactionListAdapter(val transactionList: List<DataOperation>) :
        RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {

        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.transaction_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.tvAmount.text = transaction.amount.toString()
        holder.tvAccount.text = transaction.account
        holder.tvTransactionType.text = transaction.operationType
        holder.tvCurrency.text = transaction.currency
        holder.tvCategory.text = transaction.category
    }

    override fun getItemCount() = transactionList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAmount = itemView.findViewById<TextView>(R.id.amount)
        val tvAccount = itemView.findViewById<TextView>(R.id.account)
        val tvTransactionType = itemView.findViewById<TextView>(R.id.transaction_type)
        val tvCurrency = itemView.findViewById<TextView>(R.id.currency)
        val tvCategory = itemView.findViewById<TextView>(R.id.category)
    }
}

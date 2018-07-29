package io.github.meliphant.financetracker.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.DataOperation

class TransactionRecyclerAdapter(val transactionList: List<DataOperation>) :
        RecyclerView.Adapter<TransactionRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactionList.get(position)
        holder.tvAmount.setText(transaction.amount.toString())
        holder.tvAccount.setText(transaction.account)
        holder.tvTransactionType.setText(transaction.operationType)
        holder.tvCurrency.setText(transaction.currency)
        holder.tvCategory.setText(transaction.category)
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

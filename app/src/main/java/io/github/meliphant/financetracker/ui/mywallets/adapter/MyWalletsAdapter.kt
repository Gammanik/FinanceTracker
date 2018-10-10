package io.github.meliphant.financetracker.ui.mywallets.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Wallet
import kotlinx.android.synthetic.main.item_my_wallet.view.*

class MyWalletsAdapter(var list: List<Wallet>)
    : RecyclerView.Adapter<MyWalletsAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_wallet, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun setData(newData: List<Wallet>) {
            list = newData
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item: Wallet = list[position]

            Glide.with(holder.itemView.context)
                    .load(getImage(holder.itemView.context, item.walletIconUrl))
                    .into(holder.image)

            holder.name.text = item.walletName
            holder.amount.text = "${item.money.amount}${item.money.currency.sign}"
            //todo: let the user change default currency on the way and use spinner?
            //a have to recount and convert every transaction in this wallet then
            holder.chosenCurrency.text = item.money.currency.name
        }

        private fun getImage(cntxt: Context, imageName: String): Int {
            return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
        }


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            internal val image = itemView.my_wallet_icon
            internal val name: TextView = itemView.my_wallet_name
            internal val amount = itemView.my_wallet_amount
            internal val chosenCurrency = itemView.tv_chosen_currency
        }

}


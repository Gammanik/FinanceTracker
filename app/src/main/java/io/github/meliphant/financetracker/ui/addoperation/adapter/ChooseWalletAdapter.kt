package io.github.meliphant.financetracker.ui.addoperation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.ui.addoperation.AddOperationFragment
import kotlinx.android.synthetic.main.item_wallet.view.*

class ChooseWalletAdapter(var list: List<Wallet>, private val mListener: AddOperationFragment.OnChooseWalletInteractionListener?)
    : RecyclerView.Adapter<ChooseWalletAdapter.ViewHolder>() {


    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Wallet
            mListener?.onChooseWalletInteraction(item)
        }
    }

    fun setData(newList: List<Wallet>) {
        list = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_choose_wallet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.itemView.context)
                .load(getImage(holder.itemView.context, item.walletIconUrl))
                .into(holder.image)
        holder.name.text = list[position].walletName
        holder.amount.text = item.money.amount.toString()

        holder.itemView.setOnClickListener(mOnClickListener)
        holder.itemView.tag = item
    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var image: CircleImageView = itemView.wallet_icon
        internal var name: TextView = itemView.wallet_name
        internal var amount: TextView = itemView.wallet_amount
    }


}
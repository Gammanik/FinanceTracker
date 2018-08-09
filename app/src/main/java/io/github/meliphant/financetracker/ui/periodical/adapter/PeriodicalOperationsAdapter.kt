package io.github.meliphant.financetracker.ui.periodical.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Operation
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.meliphant.financetracker.data.model.utils.OperationType
import kotlinx.android.synthetic.main.item_periodical_operation.view.*

class PeriodicalOperationsAdapter(private var mValues: List<Operation>?)
    : RecyclerView.Adapter<PeriodicalOperationsAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Operation = mValues!![position]
        holder.opComment.text = item.comment

        Glide.with(holder.mView.context)
                .load(getImage(holder.mView.context, item.category.categoryIconUrl))
                .into(holder.opCategoryImg)

        holder.opAmount.text = "${item.amountOperationCurrency.amount}${item?.wallet.money.currency.sign}"

        if (item.type == OperationType.INCOME) {
            holder.opAmount.setTextColor(holder.mView.resources.getColor(R.color.colorWalletAmount))
        }
        if (item.type == OperationType.OUTCOME) {
            holder.opAmount.setTextColor(holder.mView.resources.getColor(R.color.colorSpending))
        }

        Glide.with(holder.mView.context)
                .load(getImage(holder.mView.context, item.wallet.walletIconUrl))
                .into(holder.opWalletImg)

        holder.opWalletName.text = item.wallet.walletName

        holder.switcherIsPending.setOnClickListener{
            if ((it as Switch).isChecked) {
                holder.switcherIsPending.text = holder.switcherIsPending.textOn
            } else {
                holder.switcherIsPending.text = holder.switcherIsPending.textOff
            }
        }

        holder.switcherIsPending.isChecked = true
        if (holder.switcherIsPending.isChecked) {
            holder.switcherIsPending.text = holder.switcherIsPending.textOn
        } else {
            holder.switcherIsPending.text = holder.switcherIsPending.textOff
        }

        holder.numOfDays.text = item.periodSeconds.toString()

    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }


    fun setData(data: List<Operation>) {
        mValues = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodicalOperationsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_periodical_operation, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues!!.size
    }



    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val switcherIsPending = mView.switch_is_pending
        val numOfDays = mView.number_of_days
        val opComment: TextView = mView.op_comment
        val opCategoryImg: ImageView = mView.op_category_img
        val opWalletImg: ImageView = mView.wallet_img
        val opWalletName: TextView = mView.wallet_name
        val opAmount: TextView = mView.op_amount
    }
}
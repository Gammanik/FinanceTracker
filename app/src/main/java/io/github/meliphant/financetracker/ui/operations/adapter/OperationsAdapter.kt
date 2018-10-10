package io.github.meliphant.financetracker.ui.operations.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.utils.OperationType
import kotlinx.android.synthetic.main.item_operation.view.*
import java.text.SimpleDateFormat


class OperationsAdapter(private val mValues: List<Operation>)
    : RecyclerView.Adapter<OperationsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_operation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Operation = mValues[position]
        holder.opComment.text = item.comment
        holder.opDateTime.text = SimpleDateFormat("HH:mm dd.MM.yyyy").format(item.datetime)

        Glide.with(holder.mView.context)
                .load(getImage(holder.mView.context, item.category.categoryIconUrl))
                .into(holder.opCategoryImg)

        holder.opAmount.text = "${item.amountOperationCurrency.amount}${item.wallet.money.currency.sign}"

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
    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val opDateTime: TextView = mView.op_datetime
        val opComment: TextView = mView.op_comment
        val opCategoryImg: ImageView = mView.op_category_img
        val opWalletImg: ImageView = mView.wallet_img
        val opWalletName: TextView = mView.wallet_name
        val opAmount: TextView = mView.op_amount
    }
}

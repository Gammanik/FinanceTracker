package io.github.meliphant.financetracker.ui.addoperation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.ui.addoperation.AddOperationFragment
import kotlinx.android.synthetic.main.spinner_item_wallet.view.*

class ChooseWalletAdapter(val myContext: Context, layoutId: Int, var list: List<Wallet>, private val mListener: AddOperationFragment.OnChooseWalletInteractionListener?)
    : ArrayAdapter<Wallet>(myContext, layoutId, list) {


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




}
package io.github.meliphant.financetracker.ui.wallets.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.ui.operations.OperationListFragment

class WalletPagerAdapter(private var list: List<Wallet>, private val fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return OperationListFragment.newInstance(list[position].walletId)
    }

    override fun getCount(): Int {
        return list.size
    }

}
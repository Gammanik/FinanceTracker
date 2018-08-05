package io.github.meliphant.financetracker.ui.wallets


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.meliphant.financetracker.ADD_WALLET_ID
import io.github.meliphant.financetracker.ALL_WALLETS_ID

import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.di.component
import io.github.meliphant.financetracker.ui.wallets.adapter.WalletPagerAdapter
import io.github.meliphant.financetracker.ui.wallets.adapter.WalletRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_wallets.*
import javax.inject.Inject


class WalletsFragment : MvpAppCompatFragment(), WalletsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletsPresenter
    @ProvidePresenter
    fun provideWalletsPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.loadWallets()
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun showWallets(wallets: List<Wallet>) {
        val mutableWallets = wallets.toMutableList()
        //todo: count total balance of all wallets here
        mutableWallets.add(0, Wallet(ALL_WALLETS_ID, "all wallets", Money(2000.0, MyCurrency.USD), "wallet_allwallets"))
        mutableWallets.add(mutableWallets.size, Wallet(ADD_WALLET_ID, "add wallet", Money(0.0, MyCurrency.RUB), "ic_add"))

        view_pager.adapter = WalletPagerAdapter(mutableWallets, childFragmentManager)
        recycler_tab_layout.setUpWithAdapter(WalletRecyclerAdapter(mutableWallets, view_pager))
    }

}

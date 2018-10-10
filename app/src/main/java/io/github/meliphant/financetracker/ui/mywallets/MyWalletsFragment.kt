package io.github.meliphant.financetracker.ui.mywallets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.di.component
import io.github.meliphant.financetracker.ui.mywallets.adapter.MyWalletsAdapter
import kotlinx.android.synthetic.main.fragment_my_wallets.*
import javax.inject.Inject


class MyWalletsFragment : MvpAppCompatFragment(), MyWalletsView{

    @Inject
    @InjectPresenter lateinit var presenter: MyWalletsPresenter
    @ProvidePresenter fun provide() = presenter

    private val adapter = MyWalletsAdapter(listOf<Wallet>())

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.loadWallets()
        rv_my_wallets.adapter = adapter

        super.onViewCreated(view, savedInstanceState)
    }

    override fun updateWalletsList(wallets: List<Wallet>) {
        adapter.setData(wallets)
    }


}

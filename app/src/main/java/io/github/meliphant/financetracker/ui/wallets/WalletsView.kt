package io.github.meliphant.financetracker.ui.wallets

import com.arellomobile.mvp.MvpView
import io.github.meliphant.financetracker.data.model.Wallet

interface WalletsView: MvpView {

    fun showWallets(wallets: List<Wallet>)
}
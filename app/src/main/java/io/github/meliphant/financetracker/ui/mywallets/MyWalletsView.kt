package io.github.meliphant.financetracker.ui.mywallets

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.github.meliphant.financetracker.data.model.Wallet

interface MyWalletsView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateWalletsList(wallets: List<Wallet>)
}
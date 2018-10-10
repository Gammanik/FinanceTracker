package io.github.meliphant.financetracker.ui.diagram

import com.arellomobile.mvp.MvpView
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.CategorySpend

interface DiagramView : MvpView {

    fun showDiagramForWallet(data: List<CategorySpend>)
    fun onWalletListLoaded(data: List<Wallet>)
}
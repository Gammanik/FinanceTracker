package io.github.meliphant.financetracker.ui.addoperation

import com.arellomobile.mvp.MvpView
import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.Wallet

interface AddOperationView: MvpView {

    fun onOperationSaved()
    fun onWalletLoaded(wallet: Wallet)
    fun onWalletLoadedError()
    fun onWalletListLoaded(list: List<Wallet>)

    fun onCategoriesLoaded(categoriesList: List<MyCategory>)
}
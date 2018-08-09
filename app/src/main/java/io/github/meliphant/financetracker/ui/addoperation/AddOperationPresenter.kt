package io.github.meliphant.financetracker.ui.addoperation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.ALL_WALLETS_ID
import io.github.meliphant.financetracker.data.MainInteractor
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


@InjectViewState
class AddOperationPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<AddOperationView>() {

    private var walletsList: List<Wallet>? = null
    private var categoryList: List<MyCategory>? = null

    fun saveOperation(op: Operation) {
        launch {
            interactor.saveOperation(op)
            launch(UI) { viewState.onOperationSaved() }
        }
    }

    fun loadWalletById(walletId: Int) {
        launch { val loadedWallet = interactor.getWalletById(walletId)
            launch(UI) {
                if (loadedWallet != null) {
                    viewState.onWalletLoaded(loadedWallet)
                } else {
                    viewState.onWalletLoadedError()
                }
            }
        }
    }

    fun getWalletByIndex(index: Int): Wallet? {
        return walletsList?.get(index)
    }

    fun getWalletIndexById(walletId: Int): Int {
        return if (walletsList != null) {
            walletsList!!.indexOf(walletsList!!.find { it.walletId == walletId })
        } else {
            0
        }
    }

    fun getCategoryByIndex(index: Int): MyCategory? {
        return categoryList?.get(index)
    }

    fun loadAllWallets() {
        launch { walletsList = interactor.getAllWallets()
            launch(UI) { viewState.onWalletListLoaded(walletsList!!.map { it.walletName }) }
        }
    }

    fun loadAllCategories() {
        launch { categoryList = interactor.getAllCategories()
            launch(UI) { viewState.onCategoriesLoaded(categoryList!!) }
        }
    }
}
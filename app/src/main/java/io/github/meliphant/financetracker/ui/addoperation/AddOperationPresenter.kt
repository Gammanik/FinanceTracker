package io.github.meliphant.financetracker.ui.addoperation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


@InjectViewState
class AddOperationPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<AddOperationView>() {

    //todo: add mapper on repository
    fun saveOperation(op: Operation) {
        launch {
            interactor.saveOperation(op)
            launch(UI) { viewState.onOperationSaved() }
        }
    }

    //todo: shouldn't be here
    fun saveWallet(wallet: Wallet) {
        launch { interactor.saveWallet(wallet)
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

    fun loadAllWallets() {
        launch { val walletsList = interactor.getAllWallets()
            launch(UI) { viewState.onWalletListLoaded(walletsList) }
        }
    }

    fun loadAllCategories() {
        launch { val categories = interactor.getAllCategories()
            launch(UI) { viewState.onCategoriesLoaded(categories) }
        }
    }
}
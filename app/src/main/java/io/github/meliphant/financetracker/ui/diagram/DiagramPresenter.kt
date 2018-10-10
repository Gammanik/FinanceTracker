package io.github.meliphant.financetracker.ui.diagram

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

@InjectViewState
class DiagramPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<DiagramView>() {

    fun loadCategorySpend(walletId: Int) {
        launch {
            val data = interactor.getCategorySpent(walletId)
            launch(UI) { viewState.showDiagramForWallet(data)}
        }
    }

    fun loadWalletList() {
        launch {
            val data = interactor.getAllWallets()
            launch(UI) { viewState.onWalletListLoaded(data)}
        }
    }
}
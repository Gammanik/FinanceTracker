package io.github.meliphant.financetracker.ui.wallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

@InjectViewState
class WalletsPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<WalletsView>() {

    fun loadWallets() {
        launch {
            val walletsList = interactor.getAllWallets()
            launch(UI) { viewState.showWallets(walletsList) }
        }
    }
}
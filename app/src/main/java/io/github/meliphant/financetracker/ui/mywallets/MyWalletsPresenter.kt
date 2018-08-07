package io.github.meliphant.financetracker.ui.mywallets

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

@InjectViewState
class MyWalletsPresenter @Inject constructor(private val interactor: MainInteractor)
    : MvpPresenter<MyWalletsView>() {

    fun loadWallets() {
        launch {
            val walletsList = interactor.getAllWallets()
            launch(UI) { viewState.updateWalletsList(walletsList) }
        }
    }
}
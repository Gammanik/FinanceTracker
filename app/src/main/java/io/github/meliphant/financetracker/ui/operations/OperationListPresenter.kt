package io.github.meliphant.financetracker.ui.operations

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.ALL_WALLETS_ID
import io.github.meliphant.financetracker.data.MainInteractor
import io.github.meliphant.financetracker.data.model.Operation
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

@InjectViewState
class OperationListPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<OperationListView>() {

    fun loadOperationList(walletId: Int) {
        launch {
            val opList: List<Operation> = if(walletId == ALL_WALLETS_ID) {
                interactor.getAllOperations()
            } else {
                interactor.getOperationsByWaletId(walletId)
            }

            launch(UI) { viewState.showOperationList(opList) }
        }
    }
}
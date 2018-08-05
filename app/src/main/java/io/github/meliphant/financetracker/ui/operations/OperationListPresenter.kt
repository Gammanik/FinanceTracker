package io.github.meliphant.financetracker.ui.operations

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

@InjectViewState
class OperationListPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<OperationListView>() {

    fun loadOperationList() {
        launch {
            val opList = interactor.getAllOperations()
            launch(UI) { viewState.showOperationList(opList) }
        }
    }
}
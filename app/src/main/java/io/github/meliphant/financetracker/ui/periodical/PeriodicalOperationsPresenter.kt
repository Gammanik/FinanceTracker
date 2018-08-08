package io.github.meliphant.financetracker.ui.periodical

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import javax.inject.Inject

@InjectViewState
class PeriodicalOperationsPresenter @Inject constructor(private val interactor: MainInteractor)
    :MvpPresenter<PeriodicalOperationsView>(){

    fun loadPeriodicalOperations() {
        interactor.getAllPeriodicOperations()
    }
}
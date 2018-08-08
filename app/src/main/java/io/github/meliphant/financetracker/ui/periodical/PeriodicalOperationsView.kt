package io.github.meliphant.financetracker.ui.periodical

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.github.meliphant.financetracker.data.model.Operation

interface PeriodicalOperationsView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPeriodicalOperations(operationsList: List<Operation>)
}
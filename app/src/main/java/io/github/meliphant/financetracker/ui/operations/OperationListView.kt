package io.github.meliphant.financetracker.ui.operations

import com.arellomobile.mvp.MvpView
import io.github.meliphant.financetracker.data.model.Operation

interface OperationListView: MvpView {

    fun showOperationList(opList: List<Operation>)
}
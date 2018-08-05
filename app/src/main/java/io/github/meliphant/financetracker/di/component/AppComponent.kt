package io.github.meliphant.financetracker.di.component

import dagger.Component
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.di.module.AppModule
import io.github.meliphant.financetracker.di.module.DbModule
import io.github.meliphant.financetracker.ui.addoperation.AddOperationFragment
import io.github.meliphant.financetracker.ui.addoperation.AddOperationPresenter
import io.github.meliphant.financetracker.ui.operations.OperationListFragment
import io.github.meliphant.financetracker.ui.operations.OperationListPresenter
import javax.inject.Singleton


@Singleton @Component(modules = arrayOf(AppModule::class, DbModule::class))
interface AppComponent {
    fun inject(app: MyApp)

    fun inject(addOperationFragment: AddOperationFragment)
    fun inject(addOperationPresenter: AddOperationPresenter)

    fun inject(operationListFragment: OperationListFragment)
    fun inject(operationPresenter: OperationListPresenter)
}

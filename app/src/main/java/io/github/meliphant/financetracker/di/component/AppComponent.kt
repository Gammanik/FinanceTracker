package io.github.meliphant.financetracker.di.component

import dagger.Component
import io.github.meliphant.financetracker.MyApp
import io.github.meliphant.financetracker.di.module.AppModule
import io.github.meliphant.financetracker.di.module.DbModule
import io.github.meliphant.financetracker.ui.addoperation.AddOperationFragment
import io.github.meliphant.financetracker.ui.addoperation.AddOperationPresenter
import io.github.meliphant.financetracker.ui.diagram.DiagramFragment
import io.github.meliphant.financetracker.ui.diagram.DiagramPresenter
import io.github.meliphant.financetracker.ui.mywallets.MyWalletsFragment
import io.github.meliphant.financetracker.ui.mywallets.MyWalletsPresenter
import io.github.meliphant.financetracker.ui.operations.OperationListFragment
import io.github.meliphant.financetracker.ui.operations.OperationListPresenter
import io.github.meliphant.financetracker.ui.periodical.PeriodicalOperationsFragment
import io.github.meliphant.financetracker.ui.periodical.PeriodicalOperationsPresenter
import io.github.meliphant.financetracker.ui.wallets.WalletsFragment
import io.github.meliphant.financetracker.ui.wallets.WalletsPresenter
import javax.inject.Singleton


@Singleton @Component(modules = arrayOf(AppModule::class, DbModule::class))
interface AppComponent {
    fun inject(app: MyApp)

    fun inject(addOperationFragment: AddOperationFragment)
    fun inject(addOperationPresenter: AddOperationPresenter)

    fun inject(operationListFragment: OperationListFragment)
    fun inject(operationPresenter: OperationListPresenter)

    fun inject(walletsFragment: WalletsFragment)
    fun inject(walletsPresenter: WalletsPresenter)

    fun inject(myWalletsFragment: MyWalletsFragment)
    fun inject(myWalletsPresenter: MyWalletsPresenter)

    fun inject(diagramFragment: DiagramFragment)
    fun inject(diagramPresenter: DiagramPresenter)

    fun inject(periodicalOperationsFragment: PeriodicalOperationsFragment)
    fun inject(periodicalOperationsPresenter: PeriodicalOperationsPresenter)
}

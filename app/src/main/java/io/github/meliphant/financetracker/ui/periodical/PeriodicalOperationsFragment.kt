package io.github.meliphant.financetracker.ui.periodical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.di.component
import javax.inject.Inject

class PeriodicalOperationsFragment: MvpAppCompatFragment(), PeriodicalOperationsView{

    @Inject
    @InjectPresenter
    lateinit var presenter: PeriodicalOperationsPresenter
    @ProvidePresenter fun provide() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_periodical_operations, container, false)
    }

    override fun showPeriodicalOperations(operationsList: List<Operation>) {

    }
}
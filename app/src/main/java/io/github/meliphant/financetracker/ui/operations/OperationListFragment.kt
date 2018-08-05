package io.github.meliphant.financetracker.ui.operations

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.meliphant.financetracker.ui.operations.adapter.OperationsAdapter
import io.github.meliphant.financetracker.Keys
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import io.github.meliphant.financetracker.di.component
import kotlinx.android.synthetic.main.fragment_operation_list.*
import kotlinx.android.synthetic.main.fragment_operation_list.view.*
import java.util.*
import javax.inject.Inject

class OperationListFragment : MvpAppCompatFragment(), OperationListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: OperationListPresenter
    @ProvidePresenter fun providePresenter() = presenter

    private var walletId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)

        presenter.loadOperationList()

        arguments?.let {
            walletId = it.getInt(Keys.KEY_WALLET_ID.name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operation_list, container, false)

        val wall = Wallet(1, "myTstOpWal", Money(300.0, MyCurrency.USD), "wallet_wlcash")

//        view.rv_operations.adapter = OperationsAdapter(
//                listOf(Operation( comment = "trToPresenter",
//                amountOperationCurrency = Money(6000.0, MyCurrency.RUB),
//                amountMainCurrency = Money(6000.0/60, MyCurrency.USD),
//                wallet = wall,
//                datetime = Date(),
//                category = MyCategory(1, "tstCategory", "category_travel"),
//                type = OperationType.INCOME)
//        ))

        return view
    }

    override fun showOperationList(opList: List<Operation>) {
        rv_operations.adapter = OperationsAdapter(opList)
    }


    companion object {

        @JvmStatic
        fun newInstance(walletId: Int) =
                OperationListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(Keys.KEY_WALLET_ID.name, walletId)
                    }
                }
    }
}

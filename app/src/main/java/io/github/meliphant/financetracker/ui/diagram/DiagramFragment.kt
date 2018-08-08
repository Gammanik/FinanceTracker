package io.github.meliphant.financetracker.ui.diagram

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.utils.CategorySpend
import io.github.meliphant.financetracker.di.component
import kotlinx.android.synthetic.main.fragment_diagram.*
import javax.inject.Inject

class DiagramFragment: MvpAppCompatFragment(), DiagramView {

    @Inject
    @InjectPresenter lateinit var presenter: DiagramPresenter
    @ProvidePresenter fun provide() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diagram, container, false)
    }

    override fun onResume() {
        super.onResume()

        val yData = listOf(25.3, 10.6, 66.76, 44.32, 46.01, 16.89, 23.9)
        presenter.loadCategorySpend(1)
//        setData(yData)

    }

    private fun setData(data: List<Double>) {
        //getSpendingByCategories
        /** data format: Category, amountSpend
         * todo: make model CategorySpend(Category, amount)?
         */

        val pieEntries = data.sortedBy { it }.mapIndexed { index, fl -> PieEntry(fl.toFloat(), index) }
        val pieDataSet: PieDataSet = PieDataSet(pieEntries, "Spent by category")
        pieDataSet.sliceSpace = 3f

        val pieData = PieData(pieDataSet)
        pie_chart.data = pieData
    }

    override fun showDiagramForWallet(data: List<CategorySpend>) {
        Log.e("tg", "data got: $data")

        val pieEntries = data.sortedBy { it.amount }.mapIndexed { index, fl -> PieEntry(fl.amount.toFloat(), index) }
        val pieDataSet: PieDataSet = PieDataSet(pieEntries, "Spent by category")
        pieDataSet.sliceSpace = 3f

        val pieData = PieData(pieDataSet)
        pie_chart.data = pieData
    }

}
package io.github.meliphant.financetracker.ui.diagram

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
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
        presenter.loadCategorySpend(2)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diagram, container, false)
    }

    override fun showDiagramForWallet(data: List<CategorySpend>) {
        Log.e("tg", "data got: $data")

        val pieEntries = data.sortedBy { it.amount }.mapIndexed { index, fl -> PieEntry(fl.amount.toFloat(), index) }
        val pieDataSet: PieDataSet = PieDataSet(pieEntries, "Spent by category")
        pieDataSet.colors = ColorTemplate.LIBERTY_COLORS.toList()
        pieDataSet.sliceSpace = 3f

        val pieData = PieData(pieDataSet)
        pie_chart.data = pieData

        val description = Description()
        description.textSize = 16f
        description.text = pie_chart.resources.getString(R.string.pie_chart_description)
        pie_chart.legend.isEnabled = true

        pie_chart.description = description

        pie_chart.notifyDataSetChanged()
        pie_chart.invalidate()
    }

}
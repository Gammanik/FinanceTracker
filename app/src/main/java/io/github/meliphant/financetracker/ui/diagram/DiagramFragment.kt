package io.github.meliphant.financetracker.ui.diagram

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.CategorySpend
import io.github.meliphant.financetracker.di.component
import kotlinx.android.synthetic.main.fragment_diagram.*
import javax.inject.Inject

class DiagramFragment: MvpAppCompatFragment(), DiagramView {

    @Inject
    @InjectPresenter lateinit var presenter: DiagramPresenter
    @ProvidePresenter fun provide() = presenter

    private lateinit var walletList: List<Wallet>
    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)

        presenter.loadCategorySpend(2)
        presenter.loadWalletList()

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diagram, container, false)
    }

    override fun showDiagramForWallet(data: List<CategorySpend>) {
        val pieEntries = data.sortedBy { it.amount }
                .mapIndexed { index, catSpend -> PieEntry(catSpend.amount.toFloat(), catSpend.myCategory.categoryName) }
        
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

    override fun onWalletListLoaded(data: List<Wallet>) {
        walletList = data
        val adapter = ArrayAdapter<CharSequence>(activity, R.layout.support_simple_spinner_dropdown_item, data.map { it.walletName })
        spinner_wallet_piechart.adapter = adapter

        initSpinner()
    }

    private fun initSpinner() {

        spinner_wallet_piechart.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) { }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                presenter.loadCategorySpend(walletList[index].walletId)
            }
        }
    }


}
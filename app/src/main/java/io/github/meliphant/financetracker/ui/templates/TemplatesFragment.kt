package io.github.meliphant.financetracker.ui.templates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.di.component
import io.github.meliphant.financetracker.ui.templates.adapter.TemplatesAdapter
import kotlinx.android.synthetic.main.fragment_templates.*
import javax.inject.Inject


class TemplatesFragment: MvpAppCompatFragment(), TemplatesView {


    @Inject
    @InjectPresenter
    lateinit var presenter: TemplatesPresenter
    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.loadTemplates()
        return inflater.inflate(R.layout.fragment_templates, container, false)
    }


    override fun onTemplatesLoaded(templates: List<Operation>) {
        rv_templates.adapter = TemplatesAdapter(templates)
    }
}
package io.github.meliphant.financetracker.ui.templates

import com.arellomobile.mvp.MvpView
import io.github.meliphant.financetracker.data.model.Operation

interface TemplatesView: MvpView {

    fun onTemplatesLoaded(templates: List<Operation>)
}
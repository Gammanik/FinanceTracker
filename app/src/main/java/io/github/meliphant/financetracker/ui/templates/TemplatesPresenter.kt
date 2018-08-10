package io.github.meliphant.financetracker.ui.templates

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.meliphant.financetracker.data.MainInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

@InjectViewState
class TemplatesPresenter @Inject constructor(private val interactor: MainInteractor): MvpPresenter<TemplatesView>() {

    fun loadTemplates() {
        launch {
            val telmplateList = interactor.getTemplates()
            launch(UI) { viewState.onTemplatesLoaded(telmplateList) }
        }
    }
}
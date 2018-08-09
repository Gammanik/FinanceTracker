package io.github.meliphant.financetracker.ui.addoperation


import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import io.github.meliphant.financetracker.ALL_WALLETS_ID
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.Keys
import io.github.meliphant.financetracker.data.model.*
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import io.github.meliphant.financetracker.di.component
import io.github.meliphant.financetracker.ui.addoperation.adapter.ChooseWalletAdapter
import io.github.meliphant.financetracker.ui.wallets.WalletsFragment
import kotlinx.android.synthetic.main.fragment_add_operation.*
import java.util.*
import javax.inject.Inject


class AddOperationFragment : MvpAppCompatFragment(), AddOperationView {

    @Inject
    @InjectPresenter lateinit var presenter: AddOperationPresenter
    @ProvidePresenter fun providePresenter() = presenter


    private var walletId: Int = ALL_WALLETS_ID
    private var walletInstance: Wallet? = null
    private var categoryInstance: MyCategory? = null

    private lateinit var operationType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        activity!!.component.inject(this)
        super.onCreate(savedInstanceState)

        arguments?.let {
            walletId = it.getInt(Keys.KEY_WALLET_ID.name)
            operationType = it.getString(Keys.KEY_TRANSACTION_TYPE.name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.loadWalletById(walletId)
        initUI()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initUI() {
        presenter.loadAllWallets()
        presenter.loadAllCategories()

        initSpinners()

        initBackNavigationButton()
        initPeriodicalOperationBox()
        initSaveOperationButton()

    }

    private fun initPeriodicalOperationBox() {
        periodical_repeat_view.visibility = View.INVISIBLE
        cb_periodical_operation.setOnClickListener {
            if ((it as CheckBox).isChecked) {
                periodical_repeat_view.visibility = View.VISIBLE
            } else {
                periodical_repeat_view.visibility = View.INVISIBLE
            }
        }
    }

    private fun initBackNavigationButton() {
        toolbar.setNavigationIcon(R.drawable.toolbar_btn_back)
        toolbar.setNavigationOnClickListener({
            (context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_main, WalletsFragment())
                    .commitAllowingStateLoss()
            hideKeyboard(this.view)
        })
    }

    private fun initSaveOperationButton() {
        btn_save_operation.visibility = View.INVISIBLE
        btn_save_operation.setOnClickListener {
            if (walletInstance != null) {
                saveOperation()
            } else {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val text =p0.toString()
                if(text.isNotEmpty()) {
                    btn_save_operation.visibility = View.VISIBLE
                } else {
                    btn_save_operation.visibility = View.GONE
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun initSpinners() {
        spinner_wallet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) { }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                walletInstance = presenter.getWalletByIndex(index)
            }
        }

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) { }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                //because I inserted first element
                if(index != 0) categoryInstance = presenter.getCategoryByIndex(index - 1)
            }
        }

    }

    override fun onWalletListLoaded(walletNamesList: List<String>) {
        val tmpAdapter = ArrayAdapter<CharSequence>(activity, R.layout.support_simple_spinner_dropdown_item, walletNamesList)
        spinner_wallet.adapter = tmpAdapter

        if (walletId != ALL_WALLETS_ID) {
            spinner_wallet.setSelection(presenter.getWalletIndexById(walletId))
        } else {
            spinner_wallet.setSelection(0)
        }

    }

    override fun onCategoriesLoaded(categoriesList: List<MyCategory>) {
        val categoriesNames = categoriesList.map { it.categoryName }.toMutableList()
        categoriesNames.add(0, "Choose category")
        val tmpAdapter = ArrayAdapter<CharSequence>(activity, R.layout.support_simple_spinner_dropdown_item, categoriesNames.toList())
        spinner_category.adapter = tmpAdapter
    }

    override fun onOperationSaved() {
        hideKeyboard(this.view)
        navigateToWalletsFragment()
        Toast.makeText(requireContext(), "operation added", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(view: View?) {
        if (view?.findFocus() != null) { //hide keyboard
            val viewFocus: View = view.findFocus()
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(viewFocus.windowToken, 0)
        }
    }

    private fun navigateToWalletsFragment() {
        //todo: navigate to WalletsFragment(numInTheList)
        requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, WalletsFragment())
                .commitAllowingStateLoss()
    }

    override fun onWalletLoaded(wallet: Wallet) {
        walletId = wallet.walletId
        walletInstance = wallet

        tv_currency_sign.text = wallet.money.currency.sign.toString()
    }


    private fun saveOperation() {
        val thisOperationTypeName = figureOperationType(operationType)
        val thisOperationPeriod = if (number_of_days_to_repeat.text.toString() != "")
            number_of_days_to_repeat.text.toString().toInt() else 0

        val opToSave = Operation(
                type = OperationType.valueOf(thisOperationTypeName),
                comment = et_operation_comment.text.toString(),
                amountOperationCurrency = Money(et_amount.text.toString().toDouble(), walletInstance!!.money.currency),
                amountMainCurrency = Money(0.0, MyCurrency.USD),
                wallet = walletInstance!!,
                category = categoryInstance!!,
                datetime = Date(),
                periodSeconds = thisOperationPeriod
        )
        presenter.saveOperation(opToSave)
    }

    private fun figureOperationType(opTypeName: String): String {
        if (number_of_days_to_repeat.text.toString() != "") {
            if (opTypeName == OperationType.INCOME.name)
                return OperationType.PENDING_INCOME.name
            return if (opTypeName == OperationType.OUTCOME.name) {
                OperationType.PENDING_OUTCOME.name
            } else {
                opTypeName
            }
        } else { return opTypeName }
    }

    override fun onWalletLoadedError() { }

    interface OnChooseWalletInteractionListener {
        fun onChooseWalletInteraction(item: Wallet)
    }

    companion object {
        @JvmStatic
        fun newInstance(walletId: Int, operationType: OperationType, templateOperationId: Int?) =
                AddOperationFragment().apply {
                    arguments = Bundle().apply {
                        putInt(Keys.KEY_WALLET_ID.name, walletId)
                        putString(Keys.KEY_TRANSACTION_TYPE.name, operationType.name)
                        if (templateOperationId != null)
                            putInt(Keys.KEY_TEMPLATE_ID.name, templateOperationId)
                    }
                }
    }
}

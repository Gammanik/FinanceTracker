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
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
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

    private var TOGGLE_IS_DOWN: Boolean = false

    private var chooseWalletListener: OnChooseWalletInteractionListener? =
            object: OnChooseWalletInteractionListener{
                override fun onChooseWalletInteraction(item: Wallet) {
                    toggleChooseWallets()
                    presenter.loadWalletById(item.walletId)
                    Toast.makeText(context, "wallet chosen: ${item.walletName}", Toast.LENGTH_SHORT).show()
                }

            }
    private val chooseWalletAdapter = ChooseWalletAdapter(listOf(), chooseWalletListener)

    private var walletId: Int = ALL_WALLETS_ID
    lateinit var walletInstance: Wallet
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
        initUI()
        presenter.loadWalletById(walletId)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initUI() {
        rv_wallets_choose.visibility = View.GONE
        Glide.with(this)
                .load(getImage(requireContext(), "btn_calendar"))
                .into(btn_choose_date)

        Glide.with(this)
                .load(getImage(requireContext(), "category_travel"))
                .into(btn_choose_category)

        toolbar.setNavigationIcon(R.drawable.toolbar_btn_back)
        toolbar.setNavigationOnClickListener({
            (context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_main, WalletsFragment())
                    .commitAllowingStateLoss()
        })
        btn_choose_wallet.setOnClickListener({ toggleChooseWallets() })
        rv_wallets_choose.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_wallets_choose.adapter = chooseWalletAdapter
    }

    private fun toggleChooseWallets() {
        if (TOGGLE_IS_DOWN) {
            TOGGLE_IS_DOWN = false
            rv_wallets_choose.visibility = View.GONE

        } else {
            TOGGLE_IS_DOWN = true
            presenter.loadAllWallets()
        }
    }

    override fun expandChooseWallets(list: List<Wallet>) {
        rv_wallets_choose.visibility = View.VISIBLE
        chooseWalletAdapter.setData(list)
    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }

    override fun onOperationSaved() {
        if (this.view != null) { //hide keyboard
            val view: View = this.view!!.findFocus()
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        navigateToWalletsFragment()
        Toast.makeText(requireContext(), "OPERATION ADDED!", Toast.LENGTH_SHORT).show()
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
        btn_save_operation.setOnClickListener {saveOperation()}

        choose_wallet_text.text = wallet.walletName
        Glide.with(this)
                .load(getImage(requireContext(), wallet.walletIconUrl))
                .into(btn_choose_wallet)

         et_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val text =p0.toString()
                if(text.isNotEmpty()) {
                    btn_save_operation.visibility = View.VISIBLE
                } else {
                    btn_save_operation.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }
        })
    }


    private fun saveOperation() {
        //todo: get category from view
        var periodSeconds: Int = 0

        if (et_repeat_days.text != null && et_repeat_days.text.toString() != "") {
            periodSeconds = et_repeat_days.text.toString().toInt()
        }


        val opToSave = Operation(
                type = OperationType.valueOf(operationType),
                comment = et_operation_comment.text.toString(),
                amountOperationCurrency = Money(et_amount.text.toString().toDouble(), walletInstance.money.currency),
                amountMainCurrency = Money(0.0, MyCurrency.USD),
                wallet = walletInstance, category = MyCategory(1, "groceries", "category_groceries"),
                datetime = Date(),
                isPeriodic = periodSeconds > 0,
                periodSeconds = periodSeconds
        )
        presenter.saveOperation(opToSave)
    }

    override fun onWalletLoadedError() {
        choose_wallet_text.text = getString(R.string.choose_wallet)
        Glide.with(this)
                .load(getImage(requireContext(), "wallet_choose_wallet"))
                .into(btn_choose_wallet)
        tv_currency_sign.text = "?"
    }

    interface OnChooseWalletInteractionListener {
        fun onChooseWalletInteraction(item: Wallet)
    }

    override fun onDetach() {
        super.onDetach()
        chooseWalletListener = null
    }

    companion object {
        @JvmStatic
        fun newInstance(walletId: Int, operationType: OperationType) =
                AddOperationFragment().apply {
                    arguments = Bundle().apply {
                        putInt(Keys.KEY_WALLET_ID.name, walletId)
                        putString(Keys.KEY_TRANSACTION_TYPE.name, operationType.name)
                    }
                }
    }
}

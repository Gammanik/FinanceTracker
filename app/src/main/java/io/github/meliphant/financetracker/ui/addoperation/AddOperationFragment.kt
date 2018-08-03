package io.github.meliphant.financetracker.ui.addoperation


import android.arch.persistence.room.Room
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.Keys
import io.github.meliphant.financetracker.data.AppDb
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import kotlinx.coroutines.experimental.launch


class AddOperationFragment : Fragment() {
    private var walletId: Int = -1
    private var transactionType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            walletId = it.getInt(Keys.KEY_WALLET_ID.name)
            transactionType = it.getString(Keys.KEY_TRANSACTION_TYPE.name)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initIcons()

//        val db = Room.databaseBuilder(
//                context!!,
//                AppDb::class.java,
//                "mobileDb").build()

//        launch {
////            db.operationDao().nukeTable()
//            //only if created
////            db.walletDao().nukeTable()
////            db.walletDao().saveWallet(Wallet(walletId = 1, name = "tstWallet1", money =  Money(0.0, MyCurrency.USD), iconUrl = "wl_icon"))
//
//            val walletsList: List<Wallet> = db.walletDao().getWallets()
//            Log.e("TAG", "lst wallets $walletsList")
//
//
//            //add operation
//            Log.e("TAG", "lst ${db.operationDao().getAll()}")
//            db.operationDao()
//                    .saveOperation(IdleOperation( comment = "trTst",
//                            amountOperationCurrency = Money(6000.0, MyCurrency.RUB),
//                            amountMainCurrency = Money(6000.0/60, MyCurrency.USD),
//                            walletId = 1))
//
//            val opList: List<Operation> = db.operationDao().getAll()
//            Log.e("TAG", "lst $opList")
//            Log.e("!Tag", "maincurr:  ${opList[0].amountMainCurrency.amount} - ${opList[0].amountMainCurrency.currency}")
//        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initIcons() {
        //todo: fetch wallet by id and get all the info from it
        Glide.with(this)
                .load(getImage(requireContext(), "wallet_wlcash"))
                .into(btn_choose_wallet)

        Glide.with(this)
                .load(getImage(requireContext(), "btn_calendar"))
                .into(btn_choose_date)

        Glide.with(this)
                .load(getImage(requireContext(), "category_travel"))
                .into(btn_choose_category)
    }

    private fun getImage(cntxt: Context, imageName: String): Int {
        return cntxt.resources.getIdentifier(imageName, "drawable", cntxt.packageName)
    }

    companion object {
        @JvmStatic
        fun newInstance(walletId: Int, transactionType: OperationType) =
                AddOperationFragment().apply {
                    arguments = Bundle().apply {
                        putInt(Keys.KEY_WALLET_ID.name, walletId)
                        putString(Keys.KEY_TRANSACTION_TYPE.name, transactionType.name)
                    }
                }
    }
}

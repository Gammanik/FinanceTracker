package io.github.meliphant.financetracker.ui.addtransaction


import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.Keys
import io.github.meliphant.financetracker.data.AppDb
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.utils.OperationType
import kotlinx.coroutines.experimental.launch


class AddTransactionFragment : Fragment() {
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

        val db = Room.databaseBuilder(
                context!!,
                AppDb::class.java,
                "mobileDb").build()


        launch {
            Log.e("TAG", "lst ${db.operationDao().getAll()}")
            db.operationDao().saveOperation(Operation( name = "trTst", amount = 300.0))
            Log.e("TAG", "lst ${db.operationDao().getAll()}")
        }

        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(walletId: Int, transactionType: OperationType) =
                AddTransactionFragment().apply {
                    arguments = Bundle().apply {
                        putInt(Keys.KEY_WALLET_ID.name, walletId)
                        putString(Keys.KEY_TRANSACTION_TYPE.name, transactionType.name)
                    }
                }
    }
}

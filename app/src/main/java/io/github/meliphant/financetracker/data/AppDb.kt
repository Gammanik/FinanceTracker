package io.github.meliphant.financetracker.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import io.github.meliphant.financetracker.data.model.*
import io.github.meliphant.financetracker.data.model.dao.CategoryDao
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.dao.WalletDao
import io.github.meliphant.financetracker.data.model.dao.WalletOperationDao
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import io.github.meliphant.financetracker.data.repository.OperationRepository
import kotlinx.coroutines.experimental.launch
import java.util.*

@Database(entities = [(IdleOperation::class), (Wallet::class), (MyCategory::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDb: RoomDatabase() {
    abstract fun operationDao(): OperationDao
    abstract fun walletDao(): WalletDao
    abstract fun categoryDao(): CategoryDao
    abstract fun walletOperationDao(): WalletOperationDao


    companion object {
        @Volatile private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb=
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: getDataBase(context).also { INSTANCE = it }
                }


        private fun getDataBase(context: Context): AppDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDb::class.java, "mobileDb").addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        launch {

                            val wall1 = Wallet(1, "tstWall1-USD", Money(0.0, MyCurrency.USD), "wallet_wlcard")
                            val wall2 = Wallet(2, "wl2-RUB", Money(0.0, MyCurrency.RUB), "wallet_wlcash")
                            val walletsList = mutableListOf<Wallet>(wall1, wall2)

                            for (i in 3..10) {
                                walletsList.add(Wallet(i, "wl$i", Money(0.0, MyCurrency.RUB), "wallet_wlcard"))
                            }
                            //todo: fix problem with first launch no wallets are shown
                            getInstance(context).walletDao().saveWalletList(walletsList)

                            val categoryGroceries = MyCategory(1, "Groceries", "category_groceries")
                            val categoryFreelance = MyCategory(2, "Freelance", "category_freelance")
                            val categorySalary = MyCategory(3, "Salary", "category_salary")
                            val categoryList= listOf(categoryGroceries, categoryFreelance, categorySalary)
                            getInstance(context).categoryDao().saveCategoryList(categoryList)

                            saveOperations(context, wall1, categoryFreelance, wall2, categorySalary, categoryGroceries)
                        }
                    }
                })
                .build()
            }

            return INSTANCE as AppDb
        }


        private fun saveOperations(context: Context, wl1: Wallet, cat1: MyCategory, wl2: Wallet, cat2: MyCategory, cat3: MyCategory) {
            val op1 = Operation( comment = "earned on freelance",
                    amountOperationCurrency = Money(6000.0, MyCurrency.RUB),
                    amountMainCurrency = Money(6000.0/60, MyCurrency.USD),
                    wallet = wl1, datetime = Date(),
                    category = cat1,
                    type = OperationType.INCOME,
                    periodSeconds = 0)

            val op2 = Operation( comment = "got my salary", amountOperationCurrency = Money(7700.0, MyCurrency.USD),
                    amountMainCurrency = Money(7700.0, MyCurrency.USD),
                    wallet = wl2, datetime = Date(),
                    category = cat2, type = OperationType.INCOME,
                    periodSeconds = 0)

            val op3 = Operation( comment = "bough some food",
                    amountOperationCurrency = Money(5300.0, MyCurrency.USD),
                    amountMainCurrency = Money(5300.0, MyCurrency.USD),
                    wallet = wl2,
                    datetime = Date(),
                    category = cat3,
                    type = OperationType.OUTCOME,
                    periodSeconds = 0)

            val opRepository = OperationRepository(getInstance(context).operationDao(), getInstance(context).walletOperationDao())

            for (i in 0..15)
                opRepository.saveOperation(op1)

            for (i in 0..20) {
                opRepository.saveOperation(op2)
                opRepository.saveOperation(op3)
            }
        }
    }



}
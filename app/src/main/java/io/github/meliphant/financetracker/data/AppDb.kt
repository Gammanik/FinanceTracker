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
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import kotlinx.coroutines.experimental.launch
import java.util.*

@Database(entities = [(IdleOperation::class), (Wallet::class), (MyCategory::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDb: RoomDatabase() {
    abstract fun operationDao(): OperationDao
    abstract fun walletDao(): WalletDao
    abstract fun categoryDao(): CategoryDao


    companion object {
        @Volatile private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb=
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: getDataBase(context).also { INSTANCE = it }
                }


        private fun getDataBase(context: Context): AppDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDb::class.java, "mobileDb")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                launch {

                                    val wall1 = Wallet(1, "tstWall1-USD", Money(0.0, MyCurrency.USD), "wallet_wlcard")
                                    val wall2 = Wallet(2, "tstWall2-RUB", Money(0.0, MyCurrency.RUB), "wallet_wlcard")
                                    getInstance(context).walletDao().saveWallet(wall1)
                                    getInstance(context).walletDao().saveWallet(wall2)

                                    val categoryGroceries = MyCategory(1, "Groceries", "category_groceries")
                                    val categoryTravel = MyCategory(2, "Travel", "category_travel")
                                    getInstance(context).categoryDao().saveCategory(categoryGroceries)
                                    getInstance(context).categoryDao().saveCategory(categoryTravel)


                                    val op1 = IdleOperation( comment = "~trInRUB",
                                            amountOperationCurrency = Money(6000.0, MyCurrency.RUB),
                                            amountMainCurrency = Money(6000.0/60, MyCurrency.USD),
                                            walletId = wall1.walletId,
                                            datetime = Date(),
                                            categoryId = categoryGroceries.categoryId,
                                            type = OperationType.INCOME)

                                    val op2 = IdleOperation( comment = "~got my money",
                                            amountOperationCurrency = Money(7700.0, MyCurrency.USD),
                                            amountMainCurrency = Money(7700.0, MyCurrency.USD),
                                            walletId = wall2.walletId,
                                            datetime = Date(),
                                            categoryId = categoryTravel.categoryId,
                                            type = OperationType.INCOME)

                                    val op3 = IdleOperation( comment = "~spend some",
                                            amountOperationCurrency = Money(5300.0, MyCurrency.USD),
                                            amountMainCurrency = Money(5300.0, MyCurrency.USD),
                                            walletId = wall2.walletId,
                                            datetime = Date(),
                                            categoryId = categoryTravel.categoryId,
                                            type = OperationType.OUTCOME)

                                    for (i in 0..15)
                                        getInstance(context).operationDao().saveOperation(op1)

                                    for (i in 0..20) {
                                        getInstance(context).operationDao().saveOperation(op2)
                                        getInstance(context).operationDao().saveOperation(op3)
                                    }
                                }
                            }
                        })
                        .build()
            }

            return INSTANCE as AppDb
        }
    }

}
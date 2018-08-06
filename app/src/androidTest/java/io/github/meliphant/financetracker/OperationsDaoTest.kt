package io.github.meliphant.financetracker

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.github.meliphant.financetracker.data.AppDb
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.dao.CategoryDao
import io.github.meliphant.financetracker.data.model.dao.OperationDao
import io.github.meliphant.financetracker.data.model.dao.WalletDao
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
import io.github.meliphant.financetracker.data.repository.OperationMapper
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class OperationsDaoTest {

    private lateinit var appDatabase: AppDb
    private lateinit var operationDao: OperationDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var walletDao: WalletDao


    private val wall1 = Wallet(1, "tstWall1-USD", Money(0.0, MyCurrency.USD), "wallet_wlcard")
    private val categoryGroceries = MyCategory(1, "Groceries", "category_groceries")

    private val tstOp = IdleOperation( idleOpId = 1,
            comment = "~trInRUB",
            amountOperationCurrency = Money(6000.0, MyCurrency.RUB),
            amountMainCurrency = Money(6000.0/60, MyCurrency.USD),
            walletId = wall1.walletId,
            datetime = Date(),
            categoryId = categoryGroceries.categoryId,
            type = OperationType.INCOME,
            isPeriodic = false,
            periodSeconds = 0)

    private val tstPeriodicalOp = IdleOperation( idleOpId = 2,
            comment = "~trIn",
            amountOperationCurrency = Money(6000.0, MyCurrency.RUB),
            amountMainCurrency = Money(6000.0/60, MyCurrency.USD),
            walletId = wall1.walletId,
            datetime = Date(),
            categoryId = categoryGroceries.categoryId,
            type = OperationType.INCOME,
            isPeriodic = true,
            periodSeconds = 0)

    @Before
    fun init() {
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDb::class.java).build()
        operationDao = appDatabase.operationDao()
        categoryDao = appDatabase.categoryDao()
        walletDao = appDatabase.walletDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun shouldReturnOperationWhenInserted() {
        categoryDao.saveCategory(categoryGroceries)
        walletDao.saveWallet(wall1)
        operationDao.saveOperation(tstOp)

        launch {
            val listOfOne = operationDao.getAll()
            launch(UI) {
                assert(listOfOne == listOf(tstOp))
                assert(listOfOne.size == 1)
                assert(OperationMapper.mapOperationToIdleOperation(listOfOne[0]) == tstOp)
            }
        }

    }

    @Test
    fun shouldReturnOperationByWalletId() {
        categoryDao.saveCategory(categoryGroceries)
        walletDao.saveWallet(wall1)
        operationDao.saveOperation(tstOp)

        launch {
            val opList = operationDao.getByWalletId(wall1.walletId)
            launch(UI) {
                assert(opList.size == 1)
                assert(OperationMapper.mapOperationToIdleOperation(opList[0]) == tstOp)
                assert(opList == listOf(tstOp))
            }
        }
    }

    @Test
    fun shouldReturnNothingWhenDbIsEmpty() {
        launch {
            val listOperations = operationDao.getAll()
            launch(UI) { assert(listOperations.isEmpty()) }
        }
    }

    @Test
    fun shouldGetOnlyPeriodicalTransactions() {
        launch {
            categoryDao.saveCategory(categoryGroceries)
            walletDao.saveWallet(wall1)
            operationDao.saveOperation(tstOp)
            operationDao.saveOperation(tstPeriodicalOp)

            val listOperations = operationDao.getAllPeriodic()
            launch(UI) {
                assert(!listOperations.isEmpty())
                assert(OperationMapper.mapOperationToIdleOperation(listOperations[0]) == tstPeriodicalOp)
            }
        }
    }



}
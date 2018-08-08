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
class CategoryDaoTest {

    private lateinit var appDatabase: AppDb
    private lateinit var categoryDao: CategoryDao
    private lateinit var operationDao: OperationDao
    private lateinit var walletDao: WalletDao

    private val wall1 = Wallet(1, "tstWall1-USD", Money(0.0, MyCurrency.USD), "wallet_wlcard")
    private val categoryGroceries = MyCategory(1, "Groceries", "category_groceries")

    private val categoryList = listOf<MyCategory>()

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




}
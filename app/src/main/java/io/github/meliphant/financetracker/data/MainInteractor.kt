package io.github.meliphant.financetracker.data

import io.github.meliphant.financetracker.data.model.MyCategory
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.utils.CategorySpend
import io.github.meliphant.financetracker.data.repository.CategoryRepository
import io.github.meliphant.financetracker.data.repository.OperationRepository
import io.github.meliphant.financetracker.data.repository.WalletRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(private val opRepo: OperationRepository,
                                         private val walletRepo: WalletRepository,
                                         private val categoryRepo: CategoryRepository) {
    /** operations **/
    fun saveOperation(op: Operation) {
        opRepo.saveOperation(op)
    }

    fun getAllOperations(): List<Operation> {
        return opRepo.getAllOperations()
    }

    fun getOperationsByWalletId(walletId: Int): List<Operation> {
        return opRepo.getOperationsByWalletId(walletId)
    }

    fun getTemplates(): List<Operation> {
        return opRepo.getTemplates()
    }

    /** wallets **/
    fun saveWallet(wallet: Wallet) {
        walletRepo.saveWallet(wallet)
    }

    fun getAllWallets(): List<Wallet> {
        return walletRepo.getAllWallets()
    }

    fun getWalletById(walletId: Int): Wallet {
        return walletRepo.getWalletById(walletId)
    }

    fun getAllPeriodicOperations(): List<Operation> {
        return opRepo.getAllPeriodicOperations()
    }

    /** categories **/
    fun getAllCategories(): List<MyCategory> {
        return categoryRepo.getAllCategories()
    }

    fun getCategorySpent(walletId: Int): List<CategorySpend> {
        return categoryRepo.getAllCategoriesSpend(walletId)
    }

}
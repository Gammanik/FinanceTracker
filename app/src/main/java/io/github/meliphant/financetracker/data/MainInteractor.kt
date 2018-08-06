package io.github.meliphant.financetracker.data

import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.repository.OperationRepository
import io.github.meliphant.financetracker.data.repository.WalletRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(private val opRepo: OperationRepository,
                                         private val walletRepo: WalletRepository) {

    fun saveOperation(op: Operation) {
        opRepo.saveOperation(op)
    }

    fun getAllOperations(): List<Operation> {
        return  opRepo.getAllOperations()
    }

    fun getOperationsByWalletId(walletId: Int): List<Operation> {
        return opRepo.getOperations(walletId)
    }

    fun saveWallet(wallet: Wallet) {
        walletRepo.saveWallet(wallet)
    }

    fun getAllWallets(): List<Wallet> {
        return walletRepo.getAllWallets()
    }

    fun getWalletById(walletId: Int): Wallet {
        return walletRepo.getWalletById(walletId)
    }
}
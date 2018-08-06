package io.github.meliphant.financetracker.data

import android.util.Log
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.repository.OperationRepository
import io.github.meliphant.financetracker.data.repository.WalletRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(private val opRepo: OperationRepository,
                                         private val walletRepo: WalletRepository) {

    fun saveOperation(op: Operation) {
        Log.e("TAG", "saveOperation called:: ${opRepo.getAllOperations()}")
        opRepo.saveOperation(op)
    }

    fun getAllOperations(): List<Operation> {
        Log.e("TAG", "getAllOperation called: ${opRepo.getAllOperations()}")
        return  opRepo.getAllOperations()
    }

    fun getOperationsByWalletId(walletId: Int): List<Operation> {
        return opRepo.getOperations(walletId)
    }

    fun saveWallet(wallet: Wallet) {
        walletRepo.saveWallet(wallet)
    }

    fun getAllWallets(): List<Wallet> {
        Log.e("TAG", "getAllWallets called: ${walletRepo.getAllWallets()}")
        return walletRepo.getAllWallets()
    }

    fun getWalletById(walletId: Int): Wallet {
        Log.e("TAG", "getWalletById called: ${walletRepo.getWalletById(walletId)}")
        return walletRepo.getWalletById(walletId)
    }
}
package io.github.meliphant.financetracker.data.repository

import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.dao.WalletDao
import javax.inject.Inject

class WalletRepository @Inject constructor(private val walletDao: WalletDao) {

    fun saveWallet(wallet: Wallet) {
        walletDao.saveWallet(wallet)
    }

    fun getAllWallets(): List<Wallet> {
        return walletDao.getWallets()
    }

    fun getWalletById(walletId: Int): Wallet {
        return walletDao.getWalletById(walletId)
    }
}
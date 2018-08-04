package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.meliphant.financetracker.data.model.Wallet

@Dao
interface WalletDao {

    @Insert
    fun saveWallet(wallet: Wallet)

    @Query("SELECT * FROM wallet")
    fun getWallets(): List<Wallet>

    @Query("SELECT * FROM wallet WHERE wallet.walletId=:walletId")
    fun getWalletById(walletId: Int): Wallet

    @Query("DELETE FROM wallet")
    fun nukeTable()
}
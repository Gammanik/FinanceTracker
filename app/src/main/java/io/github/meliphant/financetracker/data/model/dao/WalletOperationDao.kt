package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.*
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Wallet

@Dao
interface WalletOperationDao {

    @Update
    fun update(wallet: Wallet)

    @Insert
    fun insert(operation: IdleOperation)

    @Delete
    fun delete(operation: IdleOperation)

    @Transaction
    fun insertOperationAndUpdateWallet(operation: IdleOperation, wallet: Wallet) {
        insert(operation)
        update(wallet)
    }

    @Transaction
    fun deleteOperationAndUpdateWallet(operation: IdleOperation, wallet: Wallet) {
        delete(operation)
        update(wallet)
    }
}
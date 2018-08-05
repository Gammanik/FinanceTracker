package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation

@Dao
interface OperationDao {

    @Query("SELECT IdleOperation.idleOpId as operationId, IdleOperation.type, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, Wallet.*, MyCategory.*, IdleOperation.datetime FROM idleOperation INNER JOIN wallet ON idleOperation.walletId = wallet.walletId INNER JOIN mycategory ON IdleOperation.categoryId = mycategory.categoryId")
    fun getAll(): List<Operation>

    @Query("SELECT IdleOperation.idleOpId as operationId, IdleOperation.type, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, MyCategory.*, IdleOperation.datetime, Wallet.* FROM idleOperation INNER JOIN wallet ON idleOperation.walletId = wallet.walletId AND idleOperation.walletId=:wlId INNER JOIN mycategory ON IdleOperation.categoryId = mycategory.categoryId")
    fun getByWalletId(wlId: Int): List<Operation>

    @Query("DELETE FROM idleOperation")
    fun nukeTable()

    @Insert
    fun saveOperation(op: IdleOperation)

}
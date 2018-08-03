package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation

@Dao
interface OperationDao {

    @Query("SELECT IdleOperation.idleOpId as operationId, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, Wallet.* FROM idleOperation INNER JOIN wallet ON idleOperation.walletId = wallet.walletId")
    fun getAll(): List<Operation>

    @Query("DELETE FROM idleOperation")
    fun nukeTable()

    @Insert
    fun saveOperation(op: IdleOperation)


}
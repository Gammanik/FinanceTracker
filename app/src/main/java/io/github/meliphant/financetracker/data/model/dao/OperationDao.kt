package io.github.meliphant.financetracker.data.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.github.meliphant.financetracker.data.model.IdleOperation
import io.github.meliphant.financetracker.data.model.Operation

@Dao
interface OperationDao {

    @Query("""SELECT IdleOperation.idleOpId as operationId, IdleOperation.type, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, Wallet.*, MyCategory.*, IdleOperation.datetime, IdleOperation.periodSeconds
        FROM idleOperation INNER JOIN wallet ON idleOperation.walletId = wallet.walletId INNER JOIN mycategory
        ON IdleOperation.categoryId = mycategory.categoryId
        AND IdleOperation.type !=  'PENDING_INCOME' AND IdleOperation.type !=  'PENDING_OUTCOME' ORDER BY IdleOperation.datetime DESC""")
    fun getAll(): List<Operation>

    @Query("""SELECT IdleOperation.idleOpId as operationId, IdleOperation.type, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, MyCategory.*, IdleOperation.datetime, Wallet.*, IdleOperation.periodSeconds
        FROM idleOperation
        INNER JOIN wallet ON idleOperation.walletId = wallet.walletId AND idleOperation.walletId=:wlId INNER JOIN mycategory
        ON IdleOperation.categoryId = mycategory.categoryId AND IdleOperation.type != ('PENDING_OUTCOME' OR 'PENDING_INCOME') ORDER BY IdleOperation.datetime DESC""")
    fun getByWalletId(wlId: Int): List<Operation>

    @Query("""SELECT IdleOperation.idleOpId as operationId, IdleOperation.type, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, Wallet.*, MyCategory.*, IdleOperation.datetime, IdleOperation.periodSeconds
        FROM idleOperation INNER JOIN wallet ON idleOperation.walletId = wallet.walletId INNER JOIN mycategory ON IdleOperation.categoryId = mycategory.categoryId
        WHERE IdleOperation.type = 'PENDING_INCOME' OR IdleOperation.type = 'PENDING_OUTCOME' """)
    fun getAllPeriodic(): List<Operation>

    @Query("""SELECT IdleOperation.idleOpId as operationId, IdleOperation.type, IdleOperation.comment, IdleOperation.amountOp_amount, IdleOperation.amountOp_currency, IdleOperation.amountMain_amount, IdleOperation.amountMain_currency, Wallet.*, MyCategory.*, IdleOperation.datetime, IdleOperation.periodSeconds
        FROM idleOperation INNER JOIN wallet ON idleOperation.walletId = wallet.walletId INNER JOIN mycategory ON IdleOperation.categoryId = mycategory.categoryId
        WHERE IdleOperation.type = 'TEMPLATE' """)
    fun getAllTemplates(): List<Operation>

    @Query("DELETE FROM idleOperation")
    fun nukeTable()

    @Insert
    fun saveOperation(op: IdleOperation)

    @Update
    fun updateOperation(op: IdleOperation)

}
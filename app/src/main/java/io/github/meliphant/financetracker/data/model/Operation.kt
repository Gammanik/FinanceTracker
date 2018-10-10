package io.github.meliphant.financetracker.data.model


import android.arch.persistence.room.Embedded
import android.arch.persistence.room.PrimaryKey
import io.github.meliphant.financetracker.data.model.utils.OperationType
import java.util.*


data class Operation(
        @PrimaryKey(autoGenerate = true) val operationId: Int = 0,
        val type: OperationType,
        val comment: String = "",

        @Embedded(prefix = "amountOp_") val amountOperationCurrency: Money,
        @Embedded(prefix = "amountMain_") val amountMainCurrency: Money,
        @Embedded val wallet: Wallet,
        @Embedded val category: MyCategory,
        var datetime: Date,
        val periodSeconds: Int
)

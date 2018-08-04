package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import io.github.meliphant.financetracker.data.model.utils.OperationType
import java.util.Date

@Entity(foreignKeys = [
    ForeignKey(
                entity = Wallet::class,
                parentColumns = ["walletId"],
                childColumns = ["walletId"],
                onDelete = CASCADE
    ),
    ForeignKey(
            entity = MyCategory::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"]
    )]
)
data class IdleOperation(
        @PrimaryKey(autoGenerate = true) val idleOpId: Int = 0,
        val type: OperationType,
        val comment: String,

        @Embedded(prefix = "amountOp_") val amountOperationCurrency: Money,
        @Embedded(prefix = "amountMain_") val amountMainCurrency: Money,
        val walletId: Int,
        val categoryId: Int,
        val datetime: Date
)
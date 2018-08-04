package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [
    (ForeignKey(
                entity = Wallet::class,
                parentColumns = ["walletId"],
                childColumns = ["walletId"],
                onDelete = CASCADE
        ))]
)
data class IdleOperation(
        @PrimaryKey(autoGenerate = true) val idleOpId: Int = 0,
        val comment: String,

        @Embedded(prefix = "amountOp_") val amountOperationCurrency: Money,
        @Embedded(prefix = "amountMain_") val amountMainCurrency: Money,
        val walletId: Int
//        val date: Date
)
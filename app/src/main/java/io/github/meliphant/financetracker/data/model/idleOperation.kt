package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(
//        foreignKeys = [
//    (ForeignKey(
//                entity = Wallet::class,
//                parentColumns = ["id"],
//                childColumns = ["walletId"],
//                onDelete = CASCADE
//        ))]
)
data class idleOperation(
        @PrimaryKey(autoGenerate = true) val idleId: Int? = null,
        val comment: String,

        @Embedded(prefix = "amountOperation") val amountOperationCurrency: Money,
        @Embedded(prefix = "amountMain") val amountMainCurrency: Money
//        val date: Date
)
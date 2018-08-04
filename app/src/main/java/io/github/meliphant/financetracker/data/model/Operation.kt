package io.github.meliphant.financetracker.data.model


import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import io.github.meliphant.financetracker.data.model.utils.MyCategory
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import java.util.*


data class Operation(
        @PrimaryKey(autoGenerate = true) val operationId: Int = 0,
        val comment: String? = "",

        @Embedded(prefix = "amountOp_") val amountOperationCurrency: Money,
        @Embedded(prefix = "amountMain_") val amountMainCurrency: Money,
        @Embedded val wallet: Wallet
//        val date: Date
)



//        val category: MyCategory
//        val wallet: Wallet

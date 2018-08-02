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
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        val comment: String,

        @Embedded(prefix = "amountOperation") val amountOperationCurrency: Money,
        @Embedded(prefix = "amountMain") val amountMainCurrency: Money
//        val date: Date
)



//        val category: MyCategory
//        val wallet: Wallet

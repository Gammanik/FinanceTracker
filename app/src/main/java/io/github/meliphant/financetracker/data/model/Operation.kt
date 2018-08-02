package io.github.meliphant.financetracker.data.model


import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import io.github.meliphant.financetracker.data.model.utils.MyCategory

@Entity(
//        foreignKeys = [
//    (ForeignKey(
//                entity = Wallet::class,
//                parentColumns = ["id"],
//                childColumns = ["walletId"],
//                onDelete = CASCADE
//        ))]
)
data class Operation(
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        val name: String,
        val amount: Double
//        val category: MyCategory
//        val wallet: Wallet
)
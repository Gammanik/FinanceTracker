package io.github.meliphant.financetracker.data.model


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Operation(
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        val name: String,
        val amount: Double
//        var category: MyCategory? = null,
//        val wallet: Wallet? = null
)
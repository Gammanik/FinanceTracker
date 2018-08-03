package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.github.meliphant.financetracker.data.model.utils.MyCurrency

@Entity
data class Wallet(
        @PrimaryKey(autoGenerate = true) val walletId: Int? = null,
        val name: String,
        @Embedded val money: Money,
        val iconUrl: String)
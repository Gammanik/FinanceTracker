package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.github.meliphant.financetracker.data.model.utils.MyCurrency

@Entity
data class Wallet(
        @PrimaryKey(autoGenerate = true) val walletId: Int = 0,
        val walletName: String,
        @Embedded val money: Money,
        val walletIconUrl: String)
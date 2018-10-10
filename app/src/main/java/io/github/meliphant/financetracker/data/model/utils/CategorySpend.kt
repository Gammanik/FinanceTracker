package io.github.meliphant.financetracker.data.model.utils

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import io.github.meliphant.financetracker.data.model.MyCategory

data class CategorySpend(
        @Embedded val myCategory: MyCategory,
        val amount: Int)
package io.github.meliphant.financetracker.data.model.utils

import android.arch.persistence.room.Entity
import io.github.meliphant.financetracker.data.model.MyCategory

@Entity
data class CategorySpend(val myCategory: MyCategory, val amount: Int)
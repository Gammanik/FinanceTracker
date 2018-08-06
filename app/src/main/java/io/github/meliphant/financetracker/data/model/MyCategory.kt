package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class MyCategory(
        @PrimaryKey(autoGenerate = true) val categoryId: Int = 0,
        val categoryName: String,
        val categoryIconUrl: String)
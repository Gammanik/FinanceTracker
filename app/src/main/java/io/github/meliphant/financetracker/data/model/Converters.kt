package io.github.meliphant.financetracker.data.model

import android.arch.persistence.room.TypeConverter
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import java.util.*

class Converters {

    @TypeConverter
    fun currencyFromOrdinal(value: Int?): MyCurrency? {
        return if (value == null) null else MyCurrency.values()[value]
    }

    @TypeConverter
    fun currencyToOrdinal(currency: MyCurrency?): Int? {
        return currency?.ordinal
    }


    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
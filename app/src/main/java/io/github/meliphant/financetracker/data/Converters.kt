package io.github.meliphant.financetracker.data

import android.arch.persistence.room.TypeConverter
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.model.utils.OperationType
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
    fun operationTypeFromOrdinal(value: Int?): OperationType? {
        return if (value == null) null else OperationType.values()[value]
    }

    @TypeConverter
    fun operationTypeToOrdinal(operationType: OperationType?): Int? {
        return operationType?.ordinal
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
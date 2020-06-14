package com.dnar.dicodingsubmissionbfaa.data.db

import androidx.room.TypeConverter
import java.util.*

// Class Converters; Keyword : Room
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
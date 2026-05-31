package com.example.comisso.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate) = date.toString()

    @TypeConverter
    fun toLocalDate(date: String) = LocalDate.parse(date)
}
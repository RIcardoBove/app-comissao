package com.example.comisso.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {

    private val fomatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy")

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.format(fomatter)
    }

    @TypeConverter
    fun toLocalDate(date: String): LocalDate {
        return LocalDate.parse(date, fomatter)
    }
}
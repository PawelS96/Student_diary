package com.psob96.bsi_33.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun listToString(array: List<Float>): String {
        return array.joinToString(separator = SEPARATOR)
    }

    @TypeConverter
    fun stringToList(value: String): List<Float> {

        val strArray = value.split(SEPARATOR)
        return strArray.map { s -> s.toFloat() }
    }

    companion object {
        const val SEPARATOR = " "
    }
}
package com.psob96.bsi_33.database.dao

import androidx.room.Update
import androidx.room.Delete
import androidx.room.Insert

interface BaseDAO<T> {

    @Insert
    fun insert(t: T): Long

    @Insert
    fun insert(t: List<T>): LongArray

    @Delete
    fun delete(t: T)

    @Update
    fun update(t: T)

}

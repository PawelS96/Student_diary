package com.psob96.bsi_33.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence

@Dao
interface PresenceDAO : BaseDAO<Presence>{

    @Query("SELECT * FROM presence WHERE id=:id")
    fun getById(id: Int) : Presence

    @Query("SELECT * FROM presence")
    fun getAll(): List<Presence>

    @Query("SELECT COUNT(*) FROM presence WHERE studentID=:studentID AND NOT(present)")
    fun getAbsenceCount(studentID: Int) : Int
}
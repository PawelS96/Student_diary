package com.psob96.bsi_33.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.psob96.bsi_33.database.entity.Grade
import com.psob96.bsi_33.database.entity.Student

@Dao
interface GradeDAO : BaseDAO<Grade> {

    @Query("SELECT * FROM grade WHERE studentID LIKE :index")
    fun getByStudent(index: Int): List<Grade>

    @Query("SELECT * FROM grade WHERE id LIKE :id")
    fun getByID(id: Int): Grade

    @Query("SELECT * FROM grade")
    fun getAll(): List<Grade>

}
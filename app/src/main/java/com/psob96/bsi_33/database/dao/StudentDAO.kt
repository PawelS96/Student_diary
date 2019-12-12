package com.psob96.bsi_33.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.psob96.bsi_33.database.entity.Student

@Dao
interface StudentDAO : BaseDAO<Student> {

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM student WHERE `index` LIKE :id")
    fun findByIndex(id: Int): Student

}
package com.psob96.bsi_33.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.psob96.bsi_33.database.entity.Lesson

@Dao
interface LessonDAO : BaseDAO<Lesson>{

    @Query("SELECT * FROM lesson WHERE id=:id")
    fun getById(id : Int) : Lesson

    @Query("SELECT * FROM lesson")
    fun getAll(): List<Lesson>
}
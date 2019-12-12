package com.psob96.bsi_33.database.repository

import com.psob96.bsi_33.database.DatabaseAccess
import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence

class LessonRepository(db : DatabaseAccess) {

    private val lessonDAO = db.lessonDAO()
    private val presenceDAO = db.presenceDAO()

    fun insertLesson(lesson: Lesson){
        lessonDAO.insert(lesson)
    }

    fun getLesson(id : Int) : Lesson{
        return lessonDAO.getById(id)
    }

    fun getPresence(id: Int) : Presence{
        return presenceDAO.getById(id)
    }

    fun insertPresence(presence: Presence){
        presenceDAO.insert(presence)
    }

    fun updatePresence(presence: Presence){
        presenceDAO.update(presence)
    }

    fun getStudentAbsenceCount(studentID : Int) : Int{
        return presenceDAO.getAbsenceCount(studentID)
    }

}
package com.psob96.bsi_33.database.repository

import com.psob96.bsi_33.database.DatabaseAccess
import com.psob96.bsi_33.database.dao.GradeDAO
import com.psob96.bsi_33.database.entity.Grade

class GradeRepository(db: DatabaseAccess) {

    private val dao: GradeDAO = db.gradeDAO();

    fun getByStudent(studentID : Int) : List<Grade>{
        return dao.getByStudent(studentID)
    }

    fun getByID(id : Int) : Grade {
        return dao.getByID(id)
    }

    fun getAllGrades() : List<Grade>{
        return dao.getAll()
    }

    fun getStudentAverage(studentID: Int) : Double{
        return getByStudent(studentID).average()
    }

    fun getTotalAverage() : Double{
        return getAllGrades().average();
    }

    fun insert(grade: Grade) {
        dao.insert(grade)
    }

    fun insert(grades: List<Grade>) {
        dao.insert(grades)
    }

    fun delete(grade: Grade){
        dao.delete(grade)
    }

    fun update(grade: Grade){
        dao.update(grade)
    }

    private fun List<Grade>.average() : Double = map{ g -> g.value}.average()
}
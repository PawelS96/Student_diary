package com.psob96.bsi_33.database.repository

import android.content.Context
import com.psob96.bsi_33.database.DatabaseAccess
import com.psob96.bsi_33.database.dao.StudentDAO
import com.psob96.bsi_33.database.entity.Student

class StudentRepository(db: DatabaseAccess) {

    private var studentDAO: StudentDAO = db.studentDAO();

    fun insertStudent(student: Student) {

        if (getStudentByID(student.index) != null)
            throw IllegalArgumentException()

        studentDAO.insert(student)
    }

    fun getAllStudents(): List<Student> {
        return studentDAO.getAll()
    }

    fun getStudentByID(id: Int): Student? {
        return studentDAO.findByIndex(id)
    }

    fun delete(student: Student) {
        studentDAO.delete(student)
    }

    fun delete(id: Int) {
        getStudentByID(id)?.let { studentDAO.delete(it) }
    }

    fun update(student: Student) {
        studentDAO.update(student)
    }


}
package com.psob96.bsi_33

import com.psob96.bsi_33.database.repository.StudentRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

class StudentRepositoryTest : DatabaseTest() {

    private lateinit var repo: StudentRepository

    override fun beforeEach() {
        repo = StudentRepository(getDB())
    }

    @Test
    fun insertStudents() {

        val students = TestDataSource.getStudents()
        students.forEach { repo.insertStudent(it) }

        assertEquals(students, repo.getAllStudents())
    }

    @Test (expected = IllegalArgumentException::class)
    fun addDuplicateStudent(){

        val student = TestDataSource.getStudents()[0]

        repo.insertStudent(student)
        repo.insertStudent(student)
    }

    @Test
    fun updateStudent(){

        val student = TestDataSource.getStudents()[0]
        val studentID = student.index

        repo.insertStudent(student)
        student.apply { firstName = firstName.reversed() + firstName }
        repo.update(student)

        assertEquals(student, repo.getStudentByID(studentID))
    }

    @Test
    fun deleteStudent(){

        val student = TestDataSource.getStudents()[0]
        val studentID = student.index

        repo.delete(student)
        assertNull(repo.getStudentByID(studentID))
    }

}

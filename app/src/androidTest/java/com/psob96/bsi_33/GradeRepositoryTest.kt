package com.psob96.bsi_33

import com.psob96.bsi_33.database.repository.GradeRepository
import com.psob96.bsi_33.database.repository.StudentRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GradeRepositoryTest : DatabaseTest() {

    private lateinit var repo: GradeRepository
    private lateinit var studentRepo: StudentRepository

    override fun beforeEach() {
        repo = GradeRepository(getDB())
        studentRepo = StudentRepository(getDB())
        TestDataSource.getStudents().forEach(studentRepo::insertStudent)
    }

    @Test
    fun insertGrades() {

        val grades = TestDataSource.getGrades()
        repo.insert(grades)

        assertEquals(grades, repo.getAllGrades())
    }

    @Test
    fun updateGrade() {
        val grade = TestDataSource.getGrades()[0]
        val id = grade.id
        repo.insert(grade)
        grade.apply { value = 5f }
        repo.update(grade)

        assertEquals(grade, repo.getByID(id))
    }

    @Test
    fun deleteGrade() {
        val grade = TestDataSource.getGrades()[0]
        val id = grade.id
        repo.insert(grade)
        repo.delete(grade)

        assertNull(repo.getByID(id))
    }

    @Test
    fun getByStudent() {

        val studentID = TestDataSource.getStudents()[0].index
        val grades = TestDataSource.getGrades().filter { grade -> grade.studentID == studentID }
        repo.insert(grades)

        assertEquals(grades, repo.getByStudent(studentID))
    }

    private val delta = 0.01

    @Test
    fun getTotalAverage(){
        repo.insert(TestDataSource.getGrades())
        assertEquals(TestDataSource.TOTAL_AVERAGE, repo.getTotalAverage(),delta )
    }

    @Test
    fun getStudentAverage(){
        repo.insert(TestDataSource.getGrades())
        assertEquals(TestDataSource.studentAverage(1), repo.getStudentAverage(1), delta)
    }

    @Test
    fun onStudentDeleted() {
        val grade = TestDataSource.getGrades()[0]
        val studentID = grade.studentID

        studentRepo.delete(studentID)

        assertNull(repo.getByID(grade.id))
    }

}
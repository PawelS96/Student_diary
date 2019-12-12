package com.psob96.bsi_33

import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence
import com.psob96.bsi_33.database.repository.LessonRepository
import com.psob96.bsi_33.database.repository.StudentRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class LessonRepositoryTest : DatabaseTest() {

    private lateinit var repo: LessonRepository
    private lateinit var studentRepo: StudentRepository

    override fun beforeEach() {
        repo = LessonRepository(getDB())
        studentRepo = StudentRepository(getDB())
    }

    @Test
    fun insertLessonTest() {

        val lesson = Lesson(1, Date().time)
        repo.insertLesson(lesson)
        assertEquals(lesson, repo.getLesson(lesson.id))
    }

    @Test
    fun insertUpdatePresence() {

        val lesson = Lesson(1, Date().time)
        repo.insertLesson(lesson)
        val student = TestDataSource.getStudents()[0]
        studentRepo.insertStudent(student)
        val presence = Presence(1, student.index, lesson.id, true)
        repo.insertPresence(presence)
        assertEquals(presence, repo.getPresence(presence.id))

        presence.present = !presence.present
        repo.updatePresence(presence)
        assertEquals(presence, repo.getPresence(presence.id))
    }

    @Test
    fun getStudentAbsenceCount() {

        val student = TestDataSource.getStudents()[0]
        studentRepo.insertStudent(student)

        val lessons = getLessonsList()
        lessons.forEach { lesson: Lesson -> repo.insertLesson(lesson) }

        val presenceList = listOf(

            Presence(1, student.index, lessons[0].id, true),
            Presence(2, student.index, lessons[1].id, false),
            Presence(3, student.index, lessons[2].id, false)
        )

        presenceList.forEach {p: Presence -> repo.insertPresence(p)}
        assertEquals(2, repo.getStudentAbsenceCount(student.index))
    }

    private fun getLessonsList(): List<Lesson> {
        return listOf(
            Lesson(1, Date().time),
            Lesson(2, Date().time),
            Lesson(3, Date().time)
        )
    }
}
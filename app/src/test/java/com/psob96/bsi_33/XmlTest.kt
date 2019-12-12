package com.psob96.bsi_33

import com.psob96.bsi_33.database.entity.Grade
import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence
import com.psob96.bsi_33.database.entity.Student
import org.junit.Assert.assertEquals
import org.junit.Test

class XmlTest {

    @Test
    fun toXmlTest(){

        val students = TestDataSource2.getStudents()
        val grades = TestDataSource2.getGrades()
        val presences = TestDataSource2.getPresences()
        val lessons = TestDataSource2.getLessons()

        val parser = XmlMapper()
        val doc = parser.export(students, grades, lessons, presences)

        assertEquals(lessons, parser.fromXML<Lesson>(doc))
        assertEquals(students, parser.fromXML<Student>(doc))
        assertEquals(grades, parser.fromXML<Grade>(doc))
        assertEquals(presences, parser.fromXML<Presence>(doc))

        println(doc.toString2())
    }
}
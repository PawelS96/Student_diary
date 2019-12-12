package com.psob96.bsi_33

import com.psob96.bsi_33.database.entity.Grade
import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence
import com.psob96.bsi_33.database.entity.Student

object TestDataSource2 {

    fun getStudents() : List<Student> {

        return listOf(
            Student(1, "Jan", "Kowalski"),
            Student(2, "Michał", "Kubina"),
            Student(3, "Przemysław", "Suszek"),
            Student(4, "Zdzisław", "Nowak"),
            Student(5, "Dariusz", "Chłopek")
        )
    }

    val TOTAL_AVERAGE = getGrades().map { g -> g.value }.average()

    fun studentAverage(id: Int) = getGrades().filter { g -> g.studentID == id }.map { g -> g.value }.average()

    fun getGrades() : List<Grade> {

        return listOf(
            Grade(1, 3f, 1),
            Grade(2, 5f, 1),
            Grade(3, 3.5f, 2),
            Grade(4, 2f, 3),
            Grade(5, 4.5f, 3),
            Grade(6, 5f, 4),
            Grade(7, 3f, 4),
            Grade(8, 2f, 5),
            Grade(9, 3.5f, 5),
            Grade(10, 5f, 5)
        )
    }

    fun getLessons() : List<Lesson> {

        return listOf(
            Lesson(1, 3),
            Lesson(2, 5),
            Lesson(3, 7),
            Lesson(4, 9)

        )
    }

    fun getPresences() : List<Presence> {

        return listOf(
           Presence(1, 1, 1, true),
            Presence(2, 2, 1, true),
            Presence(3, 3, 1, false),
            Presence(4, 4, 1, true),
            Presence(5, 5, 2, true)

        )
    }


}
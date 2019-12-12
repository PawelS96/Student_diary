package com.psob96.bsi_33.database.entity

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("index"),
            childColumns = arrayOf("studentID"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Lesson::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("lessonID"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["studentID"], name = "idxPresenceStudent"),
        Index(value = ["lessonID"], name = "idxPresenceLesson")]
)

data class Presence(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var studentID: Int = 0,
    var lessonID: Int = 0,
    var present: Boolean = false
) {
    @Ignore
    constructor(studentID: Int, lessonID: Int, present: Boolean) : this(0, studentID, lessonID, present)
}